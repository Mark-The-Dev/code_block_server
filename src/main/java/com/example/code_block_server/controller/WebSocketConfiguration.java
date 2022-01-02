package com.example.code_block_server.controller;

import com.example.code_block_server.service.ChatSocketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfiguration implements WebSocketConfigurer {

    private final ChatSocketService chatSocketService;

    @Autowired
    WebSocketConfiguration(
            ChatSocketService chatSocketService
    ) {
        this.chatSocketService = chatSocketService;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(new MessageSocketChannel(chatSocketService), "/websocket");
    }
}
