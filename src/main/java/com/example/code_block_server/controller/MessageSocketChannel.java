package com.example.code_block_server.controller;

import com.example.code_block_server.dto.MessageDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.util.SerializationUtils;
import org.springframework.web.socket.BinaryMessage;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

public class MessageSocketChannel extends AbstractWebSocketHandler {

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        super.handleMessage(session, message);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String msg = message.getPayload();
        System.out.println("Message is: " + msg);

        ObjectMapper objectMapper = new ObjectMapper();
        MessageDTO messageDTO = objectMapper.readValue(msg, MessageDTO.class);
        System.out.println(messageDTO.getBody());

        session.sendMessage(message);
    }

    @Override
    protected void handleBinaryMessage(WebSocketSession session, BinaryMessage message) throws Exception {
        MessageDTO messageDTO = (MessageDTO) SerializationUtils.deserialize(message.getPayload().array());

        System.out.println(messageDTO.getBody());

        session.sendMessage(message);
    }
}
