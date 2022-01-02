package com.example.code_block_server.controller;

import com.example.code_block_server.dto.ClientMessageDTO;
import com.example.code_block_server.dto.ServerMessageDTO;
import com.example.code_block_server.service.ChatSocketService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

@Service
public class MessageSocketChannel extends AbstractWebSocketHandler {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final ChatSocketService chatSocketService;

    MessageSocketChannel(
            ChatSocketService chatSocketService
    ) {
        this.chatSocketService = chatSocketService;
    }


    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        super.handleMessage(session, message);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String msg = message.getPayload();

        ServerMessageDTO serverMessage = objectMapper.readValue(msg, ServerMessageDTO.class);

        ClientMessageDTO clientMessageDTO = chatSocketService.processMessageDTO(
                serverMessage.getUserId(), serverMessage.getChatRoomId(), serverMessage.getMessage());

        String clientMessage = objectMapper.writeValueAsString(clientMessageDTO);

        session.sendMessage(new TextMessage(clientMessage));
    }
}
