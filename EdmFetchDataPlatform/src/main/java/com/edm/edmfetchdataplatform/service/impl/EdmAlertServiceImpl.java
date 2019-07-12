package com.edm.edmfetchdataplatform.service.impl;

import com.edm.edmfetchdataplatform.config.RabbitMQConfig;
import com.edm.edmfetchdataplatform.domain.EdmCondition;
import com.edm.edmfetchdataplatform.service.EdmAlertService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
     * 将单个 EdmCondition 发送到 消息队列
     * @param edmCondition
     */
    @Override
    public void sendEdmCondition(EdmCondition edmCondition) {
        if (edmCondition!=null){
            rabbitTemplate.convertAndSend(RabbitMQConfig.directExchangeName,
                    RabbitMQConfig.routingKey,
                    edmCondition);
        }
    }

    /**
     * 将多个EdmCondition 依次发送到消息列队
     * @param edmConditions
     */
    @Override
    public void sendEdmConditions(List<EdmCondition> edmConditions) {
        if (edmConditions!=null && !edmConditions.isEmpty()){
            for (EdmCondition edmCondition :
                    edmConditions) {
                rabbitTemplate.convertAndSend(RabbitMQConfig.directExchangeName, RabbitMQConfig.routingKey, edmCondition);
            }
        }
    }
}
