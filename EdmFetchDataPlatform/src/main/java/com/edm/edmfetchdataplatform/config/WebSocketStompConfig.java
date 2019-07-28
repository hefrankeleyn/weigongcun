package com.edm.edmfetchdataplatform.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import java.util.logging.Logger;

/**
 * 配置基于代理的Web消息功能
 * 注意： 该功能使用Rabbit作为消息中间件。在使用该功能是，应当让插件可用，
 * 通过执行： rabbitmq-plugins enable rabbitmq_stomp 命令，并重启RabbitMQ
 * @Date 2019-07-13
 * @Author lifei
 */
@Configuration
@EnableWebSocketMessageBroker // 表明这个配置类不仅配置了WebSocket， 还配置了基于代理的STOMP消息
public class WebSocketStompConfig implements WebSocketMessageBrokerConfigurer {

    // 日志对象
    private static Logger logger = Logger.getLogger("com.edm.edmfetchdataplatform.config.WebSocketStompConfig");

    @Value("${hefself.data.rabbitmq-relay-host}")
    private String relayHost;

    @Value("${hefself.data.rabbitmq-relay-port}")
    private int relayPort;

    @Value("${hefself.data.rabbitmq-client-login}")
    private String clientLogin;

    @Value("${hefself.data.rabbitmq-client-passcode}")
    private String clientPasscode;
    /**
     * 注册端点
     * @param registry
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/websocketRabbitmq").withSockJS();
    }

    /**
     * 配置消息代理
     * @param registry
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {

        logger.info("relayHost: " + relayHost + " , relayPort: " + relayPort);

        // 第一行， 启用 STOMP的中级代理
        registry.enableStompBrokerRelay("/topic", "/queue", "/amq/queue")
                .setRelayHost(this.relayHost)
                .setRelayPort(this.relayPort)
                .setClientLogin(this.clientLogin)
                .setClientPasscode(this.clientPasscode);
        // 第二行， 所有目的地以 "edmwebsocket" 开头的，都会路由到 @MessageMapping 注解方法上，而不会发布到代理队列或主题上
        registry.setApplicationDestinationPrefixes("/edmwebsocket");
    }
}
