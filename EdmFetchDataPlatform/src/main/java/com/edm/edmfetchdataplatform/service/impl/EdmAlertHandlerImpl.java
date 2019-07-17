package com.edm.edmfetchdataplatform.service.impl;

import com.edm.edmfetchdataplatform.domain.EdmApplyOrder;
import com.edm.edmfetchdataplatform.domain.EdmCondition;
import com.edm.edmfetchdataplatform.domain.EdmTaskResult;
import com.edm.edmfetchdataplatform.domain.translate.EdmConditionOfOrder;
import com.edm.edmfetchdataplatform.service.EdmAlertHandler;
import com.edm.edmfetchdataplatform.service.HiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
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
    /**
     * 当消息到达时触发该方法
     * 注意，如果要修改该方法名， RabbitMQConfig.java 文件中的内容也要修改
     * @param edmApplyOrder
     */
    @Override
    public void handlEdmApplyOrderAlert(EdmApplyOrder edmApplyOrder) {
        try {
            logger.info("handler begin....");
            Thread.sleep(10000L);
            System.out.println(edmApplyOrder);
            List<EdmCondition> edmConditions = edmApplyOrder.getEdmConditions();
            if (edmConditions!=null && !edmConditions.isEmpty()){
                for (EdmCondition edmCondition: edmConditions){
                    // 操作hive，生成数据编码等数据
                    EdmConditionOfOrder edmConditionOfOrder = new EdmConditionOfOrder(edmCondition, edmApplyOrder);
                    EdmTaskResult edmTaskResult = hiveService.optHiveFetchEdmTaskResult(edmConditionOfOrder);
                    // @TODO 将edmTaskResult 保存在数据表中
                    logger.info(edmTaskResult.toString());
                }
            }
            // @TODO 发送邮件， 通知申请者已经处理完毕

            logger.info("handler end....");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
