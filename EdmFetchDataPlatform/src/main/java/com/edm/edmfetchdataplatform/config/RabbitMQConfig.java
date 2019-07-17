package com.edm.edmfetchdataplatform.config;

import com.edm.edmfetchdataplatform.service.EdmAlertHandler;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Date 2019-07-11
 * @Author lifei
 */
@Configuration
public class RabbitMQConfig {

    public static final String queueName = "edm.orders";

    public static final String directExchangeName = "edm.order.exchange";

    public static final String routingKey = "edm";

    /**
     * 创建一个 队列
     * @return
     */
    @Bean
    public Queue queue(){
        return new Queue(queueName);
    }

    /**
     * 创建一个 topic 类型的exchange
     * @return
     */
    @Bean
    public DirectExchange exchange(){
        return new DirectExchange(directExchangeName);
    }

    /**
     * 创建一个绑定
     * @param queue
     * @param exchange
     * @return
     */
    @Bean
    public Binding binding(Queue queue, DirectExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with(routingKey);
    }

    /**
     * 定义一个消息监听器容器
     * 消息监听器容器里面包含一个消息监听器
     * @param connectionFactory
     * @param listenerAdapter
     * @return
     */
    @Bean
    public SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
                                                    MessageListenerAdapter listenerAdapter){
        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
        simpleMessageListenerContainer.setConnectionFactory(connectionFactory);
        simpleMessageListenerContainer.setQueueNames(queueName);
        simpleMessageListenerContainer.setMessageListener(listenerAdapter);
        return simpleMessageListenerContainer;
    }

    /**
     * 消息监听器
     * @return
     */
    @Bean
    public MessageListenerAdapter listenerAdapter(EdmAlertHandler edmAlertHandler){
        return new MessageListenerAdapter(edmAlertHandler, "handlEdmApplyOrderAlert");
    }
}
