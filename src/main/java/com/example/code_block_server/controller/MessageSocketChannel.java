package com.example.code_block_server.controller;

import com.example.code_block_server.dto.MessageDTO;
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

        session.sendMessage(new TextMessage("Thanks for the message!"));
    }

    @Override
    protected void handleBinaryMessage(WebSocketSession session, BinaryMessage message) throws Exception {
        MessageDTO messageDTO = (MessageDTO) SerializationUtils.deserialize(message.getPayload().array());

        System.out.println(messageDTO.getBody());

        session.sendMessage((WebSocketMessage<?>) messageDTO);
    }
}
