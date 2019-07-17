package com.edm.edmfetchdataplatform.service;

import com.edm.edmfetchdataplatform.domain.EdmApplyOrder;
/**
 *
 * 发送消息到消息队列
 * @Date 2019-07-12
 * @Author lifei
 */
public interface EdmAlertService {


    /**
     * 将 edmApplyOrder 发送到消息中间件
     * @param edmApplyOrder
     */
    void sendEdmApplyOrder(EdmApplyOrder edmApplyOrder);

}
