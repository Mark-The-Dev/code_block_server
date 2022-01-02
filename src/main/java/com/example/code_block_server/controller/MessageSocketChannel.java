package com.example.code_block_server.controller;

import com.example.code_block_server.dto.MessageDTO;
import com.example.code_block_server.dto.ServerMessageDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.util.SerializationUtils;
import org.springframework.web.socket.BinaryMessage;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

public class MessageSocketChannel extends AbstractWebSocketHandler {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        super.handleMessage(session, message);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String msg = message.getPayload();
        System.out.println("Message is: " + msg);

        ServerMessageDTO serverMessage = objectMapper.readValue(msg, ServerMessageDTO.class);
        // TODO: Save values into repository.

        // TODO: Create new ClientMessageDTO to send to the client.

        session.sendMessage(new TextMessage(msg));
    }
}
