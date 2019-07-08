package com.edm.edmfetchdataplatform.service.impl;

import com.edm.edmfetchdataplatform.base.query.EdmApplyOrderQuery;
import com.edm.edmfetchdataplatform.base.query.EdmApplyOrderResultQuery;
import com.edm.edmfetchdataplatform.domain.EdmApplyOrder;
import com.edm.edmfetchdataplatform.domain.EdmApplyOrderCheckResult;
import com.edm.edmfetchdataplatform.domain.status.CheckResultStatue;
import com.edm.edmfetchdataplatform.domain.status.ExamineProgressState;
import com.edm.edmfetchdataplatform.mapper.EdmApplyOrderCheckResultMapper;
import com.edm.edmfetchdataplatform.service.EdmApplyOrderCheckResultService;
import com.edm.edmfetchdataplatform.service.EdmApplyOrderService;
import com.edm.edmfetchdataplatform.tools.MyIdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * 流转单的结果
 * @Date 2019-06-27
 * @Author lifei
 */
@Service
public class EdmApplyOrderCheckResultServiceImpl implements EdmApplyOrderCheckResultService {

    @Autowired(required = false)
    private EdmApplyOrderCheckResultMapper edmApplyOrderCheckResultMapper;

    @Autowired
    private EdmApplyOrderService edmApplyOrderService;

    /**
     * 保存流转单的结果
     * @param edmApplyOrderCheckResult
     */
    @Override
    public void saveEdmApplyOrderCheckResult(EdmApplyOrderCheckResult edmApplyOrderCheckResult) {
        if (edmApplyOrderCheckResult!=null){
            if (edmApplyOrderCheckResult.getOcId() == null){
                String ocId = MyIdGenerator.createUUID();
                edmApplyOrderCheckResult.setOcId(ocId);
            }
            edmApplyOrderCheckResultMapper.saveEdmApplyOrderCheckResult(edmApplyOrderCheckResult);
        }
    }

    /**
     * 修改 edmApplyOrderResult
     * @param edmApplyOrderResultQuery
     */
    @Override
    @Transactional
    public void updateEdmApplyOrderCheckResult(EdmApplyOrderResultQuery edmApplyOrderResultQuery) {
        // 根据oid查询出 EdmApplyOrder
        if (edmApplyOrderResultQuery!=null && edmApplyOrderResultQuery.getOid() != null){
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
        }

    }

    /**
     * 将 edmApplyOrderResultQuery 的值复制到 edmApplyOrderCheckResult 中
     * @param edmApplyOrderResultQuery
     * @param edmApplyOrderCheckResult
     */
    private void copyQueryValueToEdmApplyOrderCheckResult(EdmApplyOrderResultQuery edmApplyOrderResultQuery,
                                                          EdmApplyOrderCheckResult edmApplyOrderCheckResult){

        if (edmApplyOrderResultQuery!=null && edmApplyOrderCheckResult!=null){
            Integer orderState = edmApplyOrderResultQuery.getOrderState();
            if (orderState!=null){
                // 申请组组长审核不通过
                if (ExamineProgressState.APPLY_GROUP_EXAMINE_FAIL.getStatus() == orderState){
                    edmApplyOrderCheckResult.setApplyGroupCheckStatus(CheckResultStatue.CHECK_FAIL.getState());
                }else if (ExamineProgressState.APPLY_GROUP_EXAMINE_SUCCESS.getStatus() == orderState){
                    // 申请组组长审核通过
                    edmApplyOrderCheckResult.setApplyGroupCheckStatus(CheckResultStatue.CHECK_SUCCESS.getState());
                }
            }
            // 申请组组长姓名
            if (edmApplyOrderResultQuery.getFirstCheckerUserName()!=null){
                edmApplyOrderCheckResult.setFirstCheckerUserName(edmApplyOrderResultQuery.getFirstCheckerUserName());
            }
            // 能力组确认结果
            if (edmApplyOrderResultQuery.getCapacityCheckStatue()!=null){
                edmApplyOrderCheckResult.setCapacityCheckStatus(edmApplyOrderResultQuery.getCapacityCheckStatue());
            }
            // 排期结果
            if (edmApplyOrderResultQuery.getPaiQiResult()!=null){
                edmApplyOrderCheckResult.setPaiQiResult(edmApplyOrderResultQuery.getPaiQiResult());
            }
            // 排期确认
            if (edmApplyOrderResultQuery.getPaiQiQueRenStatue()!=null){
                edmApplyOrderCheckResult.setPaiQiQueRenStatus(edmApplyOrderResultQuery.getPaiQiQueRenStatue());
            }
            // 群发方案确认
            if (edmApplyOrderResultQuery.getQunFaFangAnQueRenState()!=null){
                edmApplyOrderCheckResult.setQunFaFangAnQueRenStatus(edmApplyOrderResultQuery.getQunFaFangAnQueRenState());
            }
            // 客服组备注
            if (edmApplyOrderResultQuery.getThirdCheckBeiZhu()!=null){
                edmApplyOrderCheckResult.setThirdCheckBeiZhu(edmApplyOrderResultQuery.getThirdCheckBeiZhu());
            }
            // 数据编码
            if (edmApplyOrderResultQuery.getDataCode()!=null){
                edmApplyOrderCheckResult.setDataCode(edmApplyOrderResultQuery.getDataCode());
            }
            // 用户数据描述
            if (edmApplyOrderResultQuery.getDataUsersDescription()!=null){
                edmApplyOrderCheckResult.setDataUsersDescription(edmApplyOrderResultQuery.getDataUsersDescription());
            }
            // 实际用户数量
            if (edmApplyOrderResultQuery.getActualUserNum()!=null){
                edmApplyOrderCheckResult.setActualUserNum(edmApplyOrderResultQuery.getActualUserNum());
            }
            // 设置备注
            if (edmApplyOrderResultQuery.getEndBeiZhu()!=null){
                edmApplyOrderCheckResult.setEndBeiZhu(edmApplyOrderResultQuery.getEndBeiZhu());
            }
            // 更新时间
            edmApplyOrderCheckResult.setUpdateTime(new Date());
        }
    }

    /**
     * 根据群发流转单的id查询流转单的流转情况
     * @param oid
     * @return
     */
    @Override
    public EdmApplyOrderCheckResult findEdmApplyOrderCheckResultByOid(String oid) {
        if (oid != null){
            return edmApplyOrderCheckResultMapper.findEdmApplyOrderCheckResultByOid(oid);
        }
        return null;
    }

    @Override
    public EdmApplyOrderCheckResult findEdmApplyOrderCheckResultOcid(String ocid) {
        if (ocid == null){
            return null;
        }
        return edmApplyOrderCheckResultMapper.findEdmApplyOrderCheckResultByOcid(ocid);
    }
}
