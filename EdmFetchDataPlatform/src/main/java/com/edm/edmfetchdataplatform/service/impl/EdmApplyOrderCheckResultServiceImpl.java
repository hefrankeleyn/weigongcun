package com.edm.edmfetchdataplatform.service.impl;

import com.edm.edmfetchdataplatform.base.query.EdmApplyOrderResultQuery;
import com.edm.edmfetchdataplatform.domain.*;
import com.edm.edmfetchdataplatform.domain.status.CheckResultStatue;
import com.edm.edmfetchdataplatform.domain.status.ExamineProgressState;
import com.edm.edmfetchdataplatform.domain.status.GroupRole;
import com.edm.edmfetchdataplatform.domain.translate.EdmLiuZhuanEmailParameters;
import com.edm.edmfetchdataplatform.mapper.EdmApplyOrderCheckResultMapper;
import com.edm.edmfetchdataplatform.service.*;
import com.edm.edmfetchdataplatform.tools.MyIdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

/**
 * 流转单的结果
 *
 * @Date 2019-06-27
 * @Author lifei
 */
@Service
public class EdmApplyOrderCheckResultServiceImpl implements EdmApplyOrderCheckResultService {

    private static Logger logger = Logger.getLogger("com.edm.edmfetchdataplatform.service.impl.EdmApplyOrderCheckResultServiceImpl");
    @Autowired(required = false)
    private EdmApplyOrderCheckResultMapper edmApplyOrderCheckResultMapper;

    @Autowired
    private EdmApplyOrderService edmApplyOrderService;

    @Autowired
    private EdmerService edmerService;

    @Autowired
    private EdmConditionService edmConditionService;

    @Autowired
    private EdmAlertService edmAlertService;

    /**
     * 用于开启一个线程发送邮件，并发送STOMP消息
     */
    @Autowired
    private StompSendEmailService stompSendEmailService;

    /**
     * 保存流转单的结果
     *
     * @param edmApplyOrderCheckResult
     */
    @Override
    public void saveEdmApplyOrderCheckResult(EdmApplyOrderCheckResult edmApplyOrderCheckResult) {
        if (edmApplyOrderCheckResult != null) {
            if (edmApplyOrderCheckResult.getOcId() == null) {
                String ocId = MyIdGenerator.createUUID();
                edmApplyOrderCheckResult.setOcId(ocId);
            }
            edmApplyOrderCheckResultMapper.saveEdmApplyOrderCheckResult(edmApplyOrderCheckResult);
        }
    }

    /**
     * 修改 edmApplyOrderResult
     *
     * @param edmApplyOrderResultQuery
     */
    @Override
    @Transactional
    public void updateEdmApplyOrderCheckResult(EdmApplyOrderResultQuery edmApplyOrderResultQuery, String currentLoginUserEmail) {
        // 根据oid查询出 EdmApplyOrder
        if (edmApplyOrderResultQuery != null && edmApplyOrderResultQuery.getOid() != null) {
            EdmApplyOrder edmApplyOrder = edmApplyOrderService.findEdmApplyOrderByOid(edmApplyOrderResultQuery.getOid());
            edmApplyOrder.setOrderState(edmApplyOrderResultQuery.getOrderState());
            // 修改订单的状态
            edmApplyOrderService.updateEdmApplyOrderStatus(edmApplyOrder);
            // 获取 edmApplyOrderCheckResult
            EdmApplyOrderCheckResult edmApplyOrderCheckResult = edmApplyOrder.getEdmApplyOrderCheckResult();
            // 判断 edmApplyOrderResultQuery 的值是否为空，然后拷贝到 edmApplyOrderCheckResult 对应的属性中
            copyQueryValueToEdmApplyOrderCheckResult(edmApplyOrderResultQuery, edmApplyOrderCheckResult);
            //  更新订单的结果
            edmApplyOrderCheckResultMapper.updateEdmApplyOrderCheckResult(edmApplyOrderCheckResult);

            // 发送邮件
            // 封装发送邮件所需要的参数
            EdmLiuZhuanEmailParameters edmLiuZhuanEmailParameters = new EdmLiuZhuanEmailParameters(edmApplyOrder.getOrderState());
            // 收件人
            edmLiuZhuanEmailParameters.setEmailTo(edmApplyOrderResultQuery.getEmailTo());
            // 收件人的姓名
            edmLiuZhuanEmailParameters.setEmailToUserName(edmApplyOrderResultQuery.getEmailToUserName());
            //  抄送： 抄送给当前正在审核的审核人，抄送给流转单申请者
            String currentUserEmail = edmApplyOrderResultQuery.getCurrentUserEmail();
            String applierEmail = edmApplyOrder.getEdmer().getEmail();
            List<String> fileEmails = new ArrayList<>();
            fileEmails.add(currentUserEmail);
            fileEmails.add(applierEmail);

            // 根据订单的状态获取要抄送人的权限
            List<String> roleNames = fetchRoleNamesByOrderStatus(edmApplyOrder.getOrderState());
            // 根据权限获取用户
            List<String> userEmails = edmerService.findEdmerEmailsListByRoles(roleNames);
            if (userEmails != null && !userEmails.isEmpty()) {
                for (String email : userEmails) {
                    boolean flag = true;
                    for (String fileEmail : fileEmails) {
                        if (fileEmail.equals(email)) {
                            flag = false;
                        }
                    }
                    if (flag) {
                        fileEmails.add(email);
                    }
                }

            }
            String[] emailCcs = new String[fileEmails.size()];
            edmLiuZhuanEmailParameters.setEmailCc(fileEmails.toArray(emailCcs));
            // 群发流转单的名称
            edmLiuZhuanEmailParameters.setOrderName(edmApplyOrder.getOrderName());
//            // 排期意向
            edmLiuZhuanEmailParameters.setPaiQiYiXiang(edmApplyOrder.getPaiQiYiXiang());
//            // 添加附件
            edmLiuZhuanEmailParameters.setEdmApplyFiles(edmApplyOrder.getEdmApplyFiles());

            // 根据当前人的邮箱查询当前人的信息
            Edmer currentEdmer = edmerService.findEdmerByEmail(currentUserEmail);
//            // 添加当前发件人的组别
            edmLiuZhuanEmailParameters.setGroupName(currentEdmer.getDepartment());
//            // 发邮件
            stompSendEmailService.sendEmailWithRunnerAndSTOMPMessage(currentLoginUserEmail, edmLiuZhuanEmailParameters);

            // 当流转单的状态为： 客服组审核成功的时候，将流转单对应的申请项作为消息发送到
            if (edmApplyOrder.getOrderState() == ExamineProgressState.SERVICES_GROUP_EXAMINE_SUCCESS.getStatus()) {
                // 根据oid 查出对应的EdmCondition
                List<EdmCondition> edmConditions = edmConditionService.findEdmConditionsByOid(edmApplyOrder.getOid());
                // 将 edmConditions 依次发送到消息队列，进行提数处理
                edmAlertService.sendEdmConditions(edmConditions);
            }
        }

    }


    /**
     * 根据订单的状态获取“发送邮件，抄送者的权限名称”
     *
     * @param orderStatus
     * @return
     */
    private List<String> fetchRoleNamesByOrderStatus(Integer orderStatus) {
        // 存放权限
        List<String> roleNames = new ArrayList<>();
        //  审核组审核不通过，抄送给具有审核权限的人
        if (orderStatus == ExamineProgressState.APPLY_GROUP_EXAMINE_FAIL.getStatus()) {
            roleNames.add(GroupRole.ROLE_APPLY.getRoleName());
        } else if (orderStatus == ExamineProgressState.APPLY_GROUP_EXAMINE_SUCCESS.getStatus()) {
            // 等待能力组审核，抄送给具有审核权限以及具有能力组的权限
            roleNames.add(GroupRole.ROLE_APPLY.getRoleName());
            roleNames.add(GroupRole.ROLE_CAPACITY.getRoleName());
        } else if (orderStatus == ExamineProgressState.POWER_GROUP_EXAMINE_FAIL.getStatus()) {
            // 能力组审核不通过，抄送给能力组、申请组
            roleNames.add(GroupRole.ROLE_APPLY.getRoleName());
            roleNames.add(GroupRole.ROLE_CAPACITY.getRoleName());
        } else if (orderStatus == ExamineProgressState.POWER_GROUP_EXAMINE_SUCCESS.getStatus()) {
            // 能力组审核通过，抄送给能力组、客服组、申请组
            roleNames.add(GroupRole.ROLE_APPLY.getRoleName());
            roleNames.add(GroupRole.ROLE_CAPACITY.getRoleName());
            roleNames.add(GroupRole.ROLE_CUSTOMER_SERVICE.getRoleName());
        } else if (orderStatus == ExamineProgressState.SERVICES_GROUP_EXAMINE_FAIL.getStatus()) {
            // 客服组审核不通过，抄送给客服组，抄送给能力组、抄送给申请组
            roleNames.add(GroupRole.ROLE_CUSTOMER_SERVICE.getRoleName());
            roleNames.add(GroupRole.ROLE_CAPACITY.getRoleName());
            roleNames.add(GroupRole.ROLE_APPLY.getRoleName());
        } else if (orderStatus == ExamineProgressState.SERVICES_GROUP_EXAMINE_SUCCESS.getStatus()) {
            // 客服组申请通过，抄送给客服组，抄送给能力组、抄送给申请组
            roleNames.add(GroupRole.ROLE_CUSTOMER_SERVICE.getRoleName());
            roleNames.add(GroupRole.ROLE_CAPACITY.getRoleName());
            roleNames.add(GroupRole.ROLE_APPLY.getRoleName());
        } else if (orderStatus == ExamineProgressState.DATA_GROUP_EXAMINE_SUCCESS.getStatus()) {
            // 数据组处理完成，抄送给客服组、能力组、申请组
            roleNames.add(GroupRole.ROLE_CUSTOMER_SERVICE.getRoleName());
            roleNames.add(GroupRole.ROLE_CAPACITY.getRoleName());
            roleNames.add(GroupRole.ROLE_APPLY.getRoleName());
        } else if (orderStatus == ExamineProgressState.DATA_GROUP_EXAMINE_FAIL.getStatus()) {
            // 数据组处理失败，抄送给客服组、能力组、申请组
            roleNames.add(GroupRole.ROLE_CUSTOMER_SERVICE.getRoleName());
            roleNames.add(GroupRole.ROLE_CAPACITY.getRoleName());
            roleNames.add(GroupRole.ROLE_APPLY.getRoleName());
        }
        return roleNames;
    }

    /**
     * 将 edmApplyOrderResultQuery 的值复制到 edmApplyOrderCheckResult 中
     *
     * @param edmApplyOrderResultQuery
     * @param edmApplyOrderCheckResult
     */
    private void copyQueryValueToEdmApplyOrderCheckResult(EdmApplyOrderResultQuery edmApplyOrderResultQuery,
                                                          EdmApplyOrderCheckResult edmApplyOrderCheckResult) {

        if (edmApplyOrderResultQuery != null && edmApplyOrderCheckResult != null) {
            Integer orderState = edmApplyOrderResultQuery.getOrderState();
            if (orderState != null) {
                // 申请组组长审核不通过
                if (ExamineProgressState.APPLY_GROUP_EXAMINE_FAIL.getStatus() == orderState) {
                    edmApplyOrderCheckResult.setApplyGroupCheckStatus(CheckResultStatue.CHECK_FAIL.getState());
                } else if (ExamineProgressState.APPLY_GROUP_EXAMINE_SUCCESS.getStatus() == orderState) {
                    // 申请组组长审核通过
                    edmApplyOrderCheckResult.setApplyGroupCheckStatus(CheckResultStatue.CHECK_SUCCESS.getState());
                }
                //  能力组审核不通过
                else if (ExamineProgressState.POWER_GROUP_EXAMINE_FAIL.getStatus() == orderState) {
                    // 审批不通过
                    edmApplyOrderCheckResult.setCapacityCheckStatus(CheckResultStatue.CHECK_FAIL.getState());
                }
                // 客服组审核不通过
                else if (ExamineProgressState.SERVICES_GROUP_EXAMINE_FAIL.getStatus() == orderState) {
                    edmApplyOrderCheckResult.setPaiQiQueRenStatus(CheckResultStatue.CHECK_CANCEL.getState());
                    edmApplyOrderCheckResult.setQunFaFangAnQueRenStatus(CheckResultStatue.CHECK_CANCEL.getState());
                }
            }
            // 申请组组长姓名
            if (edmApplyOrderResultQuery.getFirstCheckerUserName() != null) {
                edmApplyOrderCheckResult.setFirstCheckerUserName(edmApplyOrderResultQuery.getFirstCheckerUserName());
            }

            // 能力组确认结果
            if (edmApplyOrderResultQuery.getCapacityCheckStatue() != null) {
                edmApplyOrderCheckResult.setCapacityCheckStatus(edmApplyOrderResultQuery.getCapacityCheckStatue());
            }

            // 排期结果
            if (edmApplyOrderResultQuery.getPaiQiResult() != null) {
                edmApplyOrderCheckResult.setPaiQiResult(edmApplyOrderResultQuery.getPaiQiResult());
            }
            // 排期确认
            if (edmApplyOrderResultQuery.getPaiQiQueRenStatue() != null) {
                edmApplyOrderCheckResult.setPaiQiQueRenStatus(edmApplyOrderResultQuery.getPaiQiQueRenStatue());
            }
            // 群发方案确认
            if (edmApplyOrderResultQuery.getQunFaFangAnQueRenState() != null) {
                edmApplyOrderCheckResult.setQunFaFangAnQueRenStatus(edmApplyOrderResultQuery.getQunFaFangAnQueRenState());
            }
            // 客服组备注
            if (edmApplyOrderResultQuery.getThirdCheckBeiZhu() != null) {
                edmApplyOrderCheckResult.setThirdCheckBeiZhu(edmApplyOrderResultQuery.getThirdCheckBeiZhu());
            }
            // 数据编码
            if (edmApplyOrderResultQuery.getDataCode() != null) {
                edmApplyOrderCheckResult.setDataCode(edmApplyOrderResultQuery.getDataCode());
            }
            // 用户数据描述
            if (edmApplyOrderResultQuery.getDataUsersDescription() != null) {
                edmApplyOrderCheckResult.setDataUsersDescription(edmApplyOrderResultQuery.getDataUsersDescription());
            }
            // 实际用户数量
            if (edmApplyOrderResultQuery.getActualUserNum() != null) {
                edmApplyOrderCheckResult.setActualUserNum(edmApplyOrderResultQuery.getActualUserNum());
            }
            // 设置备注
            if (edmApplyOrderResultQuery.getEndBeiZhu() != null) {
                edmApplyOrderCheckResult.setEndBeiZhu(edmApplyOrderResultQuery.getEndBeiZhu());
            }
            // 更新时间
            edmApplyOrderCheckResult.setUpdateTime(new Date());
        }
    }

    /**
     * 根据群发流转单的id查询流转单的流转情况
     *
     * @param oid
     * @return
     */
    @Override
    public EdmApplyOrderCheckResult findEdmApplyOrderCheckResultByOid(String oid) {
        if (oid != null) {
            return edmApplyOrderCheckResultMapper.findEdmApplyOrderCheckResultByOid(oid);
        }
        return null;
    }

    @Override
    public EdmApplyOrderCheckResult findEdmApplyOrderCheckResultOcid(String ocid) {
        if (ocid == null) {
            return null;
        }
        return edmApplyOrderCheckResultMapper.findEdmApplyOrderCheckResultByOcid(ocid);
    }
}
