package com.edm.edmfetchdataplatform.service.impl;

import com.edm.edmfetchdataplatform.domain.EdmCondition;
import com.edm.edmfetchdataplatform.service.EdmAlertHandler;
import org.springframework.stereotype.Component;

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
     * 当消息到达时触发该方法
     *
     * @param edmCondition
     */
    @Override
    public void handlEdmConditionAlert(EdmCondition edmCondition) {
        try {
            logger.info("handler begin....");
            Thread.sleep(30000L);
            System.out.println(edmCondition);
            logger.info("handler end....");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
