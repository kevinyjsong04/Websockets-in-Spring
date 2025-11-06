package com.example.demo.config;

import com.example.demo.websocket.SequenceWebSocketHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
    
    private final SequenceWebSocketHandler sequenceWebSocketHandler;
    
    public WebSocketConfig(SequenceWebSocketHandler sequenceWebSocketHandler) {
        this.sequenceWebSocketHandler = sequenceWebSocketHandler;
    }
    
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(sequenceWebSocketHandler, "/ws/sequence")
                .setAllowedOrigins("*");
    }
}
