package com.edm.edmfetchdataplatform.service.impl;

import com.edm.edmfetchdataplatform.config.RabbitMQConfig;
import com.edm.edmfetchdataplatform.domain.EdmApplyOrder;
import com.edm.edmfetchdataplatform.service.EdmAlertService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 发送消息到消息队列
 * @Date 2019-07-12
 * @Author lifei
 */
@Service
public class EdmAlertServiceImpl implements EdmAlertService {


    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 发送EdmApplyOrder 到消息中间件
     * @param edmApplyOrder
     */
    @Override
    public void sendEdmApplyOrder(EdmApplyOrder edmApplyOrder) {
        if (edmApplyOrder != null){
            rabbitTemplate.convertAndSend(RabbitMQConfig.directExchangeName,
                    RabbitMQConfig.routingKey,
                    edmApplyOrder);
        }
    }
}
