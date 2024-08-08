package com.example.cling.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.*;

@Configuration
@RequiredArgsConstructor
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws")
                .setAllowedOriginPatterns("*");
        //SockJS 연결 주소
        //.withSockJS(); //버전 낮은 브라우저에서도 적용 가능
        // 주소 : ws://localhost:1234/ws
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/sub");//클라이언트에서 보낸 메세지를 받을 prefix
        //구독 요청 경로 /sub/room/{roomId}
        registry.setApplicationDestinationPrefixes("/pub");//해당 주소를 구독하고 있는 클라이언트들에게 메세지 전달
        //전달 요청 경로 /pub/room/{roomId}
    }
}
