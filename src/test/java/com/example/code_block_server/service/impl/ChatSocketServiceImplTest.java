package com.example.code_block_server.service.impl;

import com.example.code_block_server.entity.ChatMessageEntity;
import com.example.code_block_server.entity.ChatRoomEntity;
import com.example.code_block_server.entity.UserEntity;
import com.example.code_block_server.repository.ChatMessageRepository;
import com.example.code_block_server.repository.ChatRoomRepository;
import com.example.code_block_server.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ChatSocketServiceImplTest {

    @InjectMocks
    private ChatSocketServiceImpl chatSocketService;

    @Mock
    private ChatRoomRepository chatRoomRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ChatMessageRepository chatMessageRepository;

    @Captor
    ArgumentCaptor<ChatMessageEntity> chatMessageCaptor;


    @Test
    public void processMessageDTO_shouldSaveChatMessage() {

        UserEntity userEntity = new UserEntity();
        userEntity.setId(12);
        userEntity.setFirstName("T-bone");
        userEntity.setLastName("FreshSqueeze");

        ChatRoomEntity chatRoomEntity = new ChatRoomEntity();
        chatRoomEntity.setId(15L);

        when(userRepository.getById(12L)).thenReturn(userEntity);
        when(chatRoomRepository.findById(15L)).thenReturn(chatRoomEntity);

        String messageToSend = "Hello from the other sideeeee!";

        chatSocketService.processMessageDTO(12L, 15L, messageToSend);

        verify(chatMessageRepository).save(chatMessageCaptor.capture());
        ChatMessageEntity capturedMessage = chatMessageCaptor.getValue();
        assertEquals(capturedMessage.getMessage(), "Hello from the other sideeeee!");
        assertEquals(capturedMessage.getUser().getFirstName(), "T-bone");
    }

}
