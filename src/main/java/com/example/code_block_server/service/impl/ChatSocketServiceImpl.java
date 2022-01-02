package com.example.code_block_server.service.impl;

import com.example.code_block_server.dto.ClientMessageDTO;
import com.example.code_block_server.entity.ChatMessageEntity;
import com.example.code_block_server.entity.ChatRoomEntity;
import com.example.code_block_server.entity.UserEntity;
import com.example.code_block_server.repository.ChatMessageRepository;
import com.example.code_block_server.repository.ChatRoomRepository;
import com.example.code_block_server.repository.UserRepository;
import com.example.code_block_server.service.ChatSocketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Implementation level of Chat Socket Service
 */

@Service
public class ChatSocketServiceImpl implements ChatSocketService {

    private final ChatMessageRepository chatMessageRepository;
    private final UserRepository userRepository;
    private final ChatRoomRepository chatRoomRepository;
    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    @Autowired
    ChatSocketServiceImpl(
            ChatMessageRepository chatMessageRepository,
            UserRepository userRepository,
            ChatRoomRepository chatRoomRepository
    ) {
        this.chatMessageRepository = chatMessageRepository;
        this.userRepository = userRepository;
        this.chatRoomRepository = chatRoomRepository;
    }

    /**
     *  Service method that saves a message into the database from a to-server DTO
     *  and generates a to-client DTO.
     * @param userId the id of the user sending the message.
     * @param chatroomId the id of the chat room the message goes to.
     * @param message the message string
     * @return a client ready message DTO
     */
    @Override
    public ClientMessageDTO processMessageDTO(long userId, long chatroomId, String message) {

        // Find user and chatroom entity from repositories.
        UserEntity userEntity = userRepository.findById(userId);
        ChatRoomEntity chatRoomEntity = chatRoomRepository.findById(chatroomId);

        if (userEntity == null || chatRoomEntity == null){
            throw new IllegalArgumentException("There is no User or chatroom associated");
        }

        // Generate and persist chat message for database.
        ChatMessageEntity chatMessageEntity = new ChatMessageEntity();
        chatMessageEntity.setMessage(message);
        chatMessageEntity.setUser(userEntity);
        chatMessageEntity.setChatRoom(chatRoomEntity);
        chatMessageEntity.setCreatedAt(new Date());
        chatMessageRepository.save(chatMessageEntity);

        // Generate String values for client object.
        String timeString = simpleDateFormat.format(chatMessageEntity.getCreatedAt());
        String userName = String.format("%s %s", userEntity.getFirstName(), userEntity.getLastName());

        return new ClientMessageDTO(message, userName, timeString);
    }
}
