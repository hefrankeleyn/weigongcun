package com.edm.edmfetchdataplatform.service.impl;

import com.edm.edmfetchdataplatform.config.DataConfig;
import com.edm.edmfetchdataplatform.domain.*;
import com.edm.edmfetchdataplatform.domain.status.EnableOrNotStatus;
import com.edm.edmfetchdataplatform.domain.status.ExamineProgressState;
import com.edm.edmfetchdataplatform.domain.translate.EdmConditionOfOrder;
import com.edm.edmfetchdataplatform.domain.translate.EdmLiuZhuanEmailParameters;
import com.edm.edmfetchdataplatform.service.*;
import com.edm.edmfetchdataplatform.tools.MyArrayUtil;
import com.edm.edmfetchdataplatform.tools.MyDateUtil;
import com.edm.edmfetchdataplatform.tools.MyFileUtil;
import com.edm.edmfetchdataplatform.tools.MyStrUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

/**
 * 消息驱动的POJO
 *
 * @Date 2019-07-11
 * @Author lifei
 */
@Component
public class EdmAlertHandlerImpl implements EdmAlertHandler {

    private static final Logger logger = Logger.getLogger("com.edm.edmfetchdataplatform.service.impl.EdmAlertHandlerImpl");

    /**
     * 连接hive进行查询操作
     */
    @Autowired
    private HiveService hiveService;

    @Autowired
    private EdmTaskResultService edmTaskResultService;

    @Autowired
    private EdmApplyOrderCheckResultService edmApplyOrderCheckResultService;

    @Autowired
    private EdmApplyOrderService edmApplyOrderService;

    @Autowired
    private EdmZoneService edmZoneService;

    @Autowired
    private DataConfig dataConfig;

    @Autowired
    private EdmExcelService edmExcelService;

    @Autowired
    private EdmSendEmailService edmSendEmailService;

    private static final ExecutorService singleThreadService = Executors.newSingleThreadExecutor();


    /**
     * 当消息到达时触发该方法
     * 注意，如果要修改该方法名， RabbitMQConfig.java 文件中的内容也要修改
     *
     * @param edmApplyOrder
     */
    @Override
    public void handlEdmApplyOrderAlert(EdmApplyOrder edmApplyOrder) {
        Runnable r = () ->{singleRunMethodHandlEdmApplyOrderAlert(edmApplyOrder);};
        singleThreadService.submit(r);
    }

    /**
     * 当消息到达时触发该方法
     * 注意，如果要修改该方法名， RabbitMQConfig.java 文件中的内容也要修改
     * 开启一个单线程来做这件事件，让消息驱动对消息处理依次进行
     * @param edmApplyOrder
     */
    @Transactional
    public void singleRunMethodHandlEdmApplyOrderAlert(EdmApplyOrder edmApplyOrder) {
        try {
            logger.info("handler begin....");
            List<EdmCondition> edmConditions = edmApplyOrder.getEdmConditions();
            //  更新EDMApplyOrderCheckResult
            EdmApplyOrderCheckResult edmApplyOrderCheckResult = edmApplyOrder.getEdmApplyOrderCheckResult();
            if (edmConditions != null && !edmConditions.isEmpty()) {
                List<String> dataCodeList = new ArrayList<>();
                int actualUserNum = 0;
                StringBuilder userDesc = new StringBuilder();
                List<EdmTaskResult> edmTaskResultList = new ArrayList<>();
                EdmCondition edmCondition = null;
                for (int i = 0; i < edmConditions.size(); i++) {
                    edmCondition = edmConditions.get(i);
                    logger.info("edmCondition conid: " + edmCondition.getConId());
                    // 操作hive，生成数据编码等数据
                    EdmConditionOfOrder edmConditionOfOrder = new EdmConditionOfOrder(edmCondition, edmApplyOrder);
                    EdmTaskResult edmTaskResult = hiveService.optHiveFetchEdmTaskResult(edmConditionOfOrder);
                    // 将省份信息转化成字符串
                    Map<String, Integer> provinceNums = edmTaskResult.getProvinceNums();
                    if (provinceNums != null && !provinceNums.isEmpty()) {
                        String[] provinceInfos = new String[provinceNums.size()];
                        int x = 0;
                        String provinceName = null;
                        for (Map.Entry<String, Integer> entry : provinceNums.entrySet()) {
                            provinceName = edmZoneService.findProvinceNameByProvinceCode(entry.getKey());
                            provinceInfos[x++] = provinceName + ":" + entry.getValue();
                        }
                        // 设置省份信息
                        edmTaskResult.setProvinceNumsInfo(MyArrayUtil.arrayToStr(provinceInfos));
                    }
                    // 设置流转单结果的id
                    edmTaskResult.setOcId(edmApplyOrderCheckResult.getOcId());
                    //  将edmTaskResult 保存在数据表中
                    logger.info(edmTaskResult.toString());
                    edmTaskResultService.saveEdmTaskResult(edmTaskResult);
                    // 将dataCode添加到list中
                    dataCodeList.add(edmTaskResult.getDataCode());
                    // 实际的用户量
                    actualUserNum += edmTaskResult.getFileLineNum();
                    if (edmCondition.getLimitNum() > edmTaskResult.getFileLineNum()) {
                        userDesc.append("目标用户" + (i+1) + " 未提取到足量用户，比预期少提取了"
                                + (edmCondition.getLimitNum() - edmTaskResult.getFileLineNum()) + ";\r\n");
                    } else {
                        userDesc.append("目标用户" + (i+1) + " 提取的数据量与预期的一致" + ";\r\n");
                    }
                    edmTaskResultList.add(edmTaskResult);
                }

                edmApplyOrderCheckResult.setActualUserNum(actualUserNum);
                //  设置数据编码
                String[] dataCodes = new String[dataCodeList.size()];
                edmApplyOrderCheckResult.setDataCodes(MyArrayUtil.arrayToStr(dataCodeList.toArray(dataCodes)));
                // 设置 提取的用户描述
                edmApplyOrderCheckResult.setDataUsersDescription(userDesc.toString());
                edmApplyOrderCheckResultService.updateEdmApplyOrderCheckResult(edmApplyOrderCheckResult);

                // 修改订单的状态, 修改为数据组处理完成
                edmApplyOrder.setOrderState(ExamineProgressState.DATA_GROUP_EXAMINE_SUCCESS.getStatus());

                // 修改订单的状态
                edmApplyOrderService.updateEdmApplyOrderStatus(edmApplyOrder);

                // 修改edmTaskResultList ,将其状态修改为可用
                if (edmTaskResultList!=null && !edmTaskResultList.isEmpty()){
                    for (EdmTaskResult oldEdmTaskResult : edmTaskResultList) {
                        oldEdmTaskResult.setStatus(EnableOrNotStatus.enable_status.getStatus());
                        edmTaskResultService.updateEdmTaskResult(oldEdmTaskResult);
                    }
                }

                // 设置用于发送邮件写excel
                edmApplyOrderCheckResult.setEdmTaskResults(edmTaskResultList);
                // 发送邮件，通知处理完毕
                sendEmailToSuccessTasks(edmApplyOrder, edmApplyOrderCheckResult);
                // 邮件发送完成，流转任务结束，修改流转单的状态
                edmApplyOrder.setOrderState(ExamineProgressState.ORDER_SUCCESS.getStatus());
                edmApplyOrderService.updateEdmApplyOrderStatus(edmApplyOrder);
                logger.info("handler end....");
            }
        } catch (Exception e) {
            // 修改流转状态，数据组处理失败
            edmApplyOrder.setOrderState(ExamineProgressState.DATA_GROUP_EXAMINE_FAIL.getStatus());
            edmApplyOrderService.updateEdmApplyOrderStatus(edmApplyOrder);
            logger.warning(e.toString());
            throw new RuntimeException(e);
        }
    }


    /**
     * 发送邮件结束该提数任务
     */
    private void sendEmailToSuccessTasks(EdmApplyOrder edmApplyOrder, EdmApplyOrderCheckResult edmApplyOrderCheckResult) {
        // 发送邮件， 通知申请者已经处理完毕
        // 发送邮件
        // 封装发送邮件所需要的参数
        EdmLiuZhuanEmailParameters edmLiuZhuanEmailParameters =
                new EdmLiuZhuanEmailParameters(edmApplyOrder.getOrderState());
        // 收件人
        edmLiuZhuanEmailParameters.setEmailTo(edmApplyOrder.getEdmer().getEmail());
        // 收件人的姓名
        edmLiuZhuanEmailParameters.setEmailToUserName(edmApplyOrder.getEdmer().getUsername());
        // 邮件的抄送者
        Set<String> emailCcs = new HashSet<>();
        if (!MyStrUtil.isEmptyOrNull(edmApplyOrderCheckResult.getFirstCheckerEmail())) {
            emailCcs.add(edmApplyOrderCheckResult.getFirstCheckerEmail());
        }
        if (!MyStrUtil.isEmptyOrNull(edmApplyOrderCheckResult.getSecondCheckerEmail())) {
            emailCcs.add(edmApplyOrderCheckResult.getSecondCheckerEmail());
        }
        if (!MyStrUtil.isEmptyOrNull(edmApplyOrderCheckResult.getThirdCheckerEmail())) {
            emailCcs.add(edmApplyOrderCheckResult.getThirdCheckerEmail());
        }
        if (!emailCcs.isEmpty()) {
            String[] cc = new String[emailCcs.size()];
            edmLiuZhuanEmailParameters.setEmailCc(emailCcs.toArray(cc));
        }
        // 群发流转单的名称
        edmLiuZhuanEmailParameters.setOrderName(edmApplyOrder.getOrderName());
        // 排期意向
        edmLiuZhuanEmailParameters.setPaiQiYiXiang(edmApplyOrder.getPaiQiYiXiang());

        // 生成要发送的附件
        // 创建当前流转单的目录，该流转单及附件保存在同一个目录里面
        // 上传文件的根目录
        String rootPath = dataConfig.getUpLoadPath();
        // 根据根目录创建一个唯一的目录
        String uniqueFilePath = MyFileUtil.createUniqueFilePath(rootPath);
        // 将群发流转单生成excel
        // 当前时间的年月
        String currentYearMonthDayStr = MyDateUtil.toDateStr(new Date());
        String originalFilename = "《" + edmApplyOrder.getOrderName() + "》群发流转单-" + currentYearMonthDayStr + ".xlsx";
        EdmApplyFile edmApplyFile = edmExcelService.createEdmApplyExcelOrder(edmApplyOrder, edmApplyOrderCheckResult, uniqueFilePath, originalFilename);
        List<EdmApplyFile> edmApplyFiles = new ArrayList<>();
        edmApplyFiles.add(edmApplyFile);
        // 添加附件
        edmLiuZhuanEmailParameters.setEdmApplyFiles(edmApplyFiles);
        // 添加当前发件人的组别
        edmLiuZhuanEmailParameters.setGroupName(edmApplyOrder.getEdmer().getDepartment());

        logger.info(edmLiuZhuanEmailParameters.toString());
        // 发送邮件
        edmSendEmailService.sendThymeleafEmail(edmLiuZhuanEmailParameters);
    }
}
