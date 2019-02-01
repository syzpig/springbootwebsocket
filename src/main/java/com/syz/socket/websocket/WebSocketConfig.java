package com.syz.socket.websocket;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
/**
 *WebSocketMessageBrokerConfigurer实现这个类重写里面的方法区定一个连接点规则  具体看官网
 *
 * 官网：https://docs.spring.io/spring/docs/current/spring-framework-reference/web.html#websocket
 */
@Configuration
@EnableWebSocketMessageBroker  //这是websocekt的订阅器
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic");
        //客户端以请求为app开头的消息都会被处理
        //例如：app/xxx会被加了@MessageMapping("/xxx")注解的方法处理，也就是会路由到这个注解的方法
        // @SendTo("/topic/greetings")这个注解必须在带@MessageMapping的方法上面
        //sendTo其实是一个广播，就是当一个请求来，会跳到这个注解所在controller的方法，这个方法的返回值
        //就会被发送给我们订阅了/topic/greetings这个订阅器的所有客户端。因为我们的客户端也需要订阅，因为webseoket是双向
        //
        config.setApplicationDestinationPrefixes("/app");//这个是服务器端订阅的定义；这个定义发送请求时哪些请求能发送给websecket服务器。不可能所有请求都处理
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        //这个就是来定义客户端连接的协议规则  类似于数据库连接jdbc://。用于指定规则让客户端匹配连接
        //就是来定义和客户端的链接，类似打电话，先联通
        registry.addEndpoint("/syz").withSockJS();
    }

}