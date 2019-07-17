package com.edm.edmfetchdataplatform.service;

import com.edm.edmfetchdataplatform.domain.EdmApplyOrder;

public interface EdmAlertHandler {

    /**
     * 消息驱动方法
     * 注意，如果要修改该方法名， RabbitMQConfig.java 文件中的内容也要修改
     * @param edmApplyOrder
     */
    void handlEdmApplyOrderAlert(EdmApplyOrder edmApplyOrder);
}
