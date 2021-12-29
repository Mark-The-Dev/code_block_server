package com.example.code_block_server.socket_channel;

import com.example.code_block_server.dto.InboundMessageDTO;
import com.example.code_block_server.dto.OutboundMessageDTO;
import com.example.code_block_server.entity.ChatMessageEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class ChatRoomController {
    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    public OutboundMessageDTO send(final InboundMessageDTO message) {

        Date createdAt = new Date();

        // TODO: create repositories to add entity values
        ChatMessageEntity chatMessageEntity = new ChatMessageEntity();
        chatMessageEntity.setCreatedAt(createdAt);
        // chatMessageEntity.setChatRoom(chatRoom);
        // chatMessageEntity.setUser(user);
        chatMessageEntity.setMessage(message.getMessage());

        // Default username prior to repositories
        String userName = "test user";

        final String time = new SimpleDateFormat("HH:mm").format(createdAt);
        return new OutboundMessageDTO(chatMessageEntity.getMessage(), userName, time);
    }

}
