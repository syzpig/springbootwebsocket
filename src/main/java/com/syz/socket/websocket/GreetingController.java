package com.syz.socket.websocket;

import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

import java.util.Map;

@Controller
public class GreetingController {


    @MessageMapping("/hello")
    @SendTo("/topic/syz")//这个是表示客户端的订阅，就是服务器端定义以这个地址的去订阅，那个服务器端是怎么体现的呢，就是在
    //WebSocketConfig这个规则配置类中体现   config.enableSimpleBroker("/topic");
    //接收客户端传送的参数有几种方式，可看官网，这里举例两种
    public String index(@Headers Map<Object,String> map, Message message) throws Exception {

      return "testService";
    }

}
