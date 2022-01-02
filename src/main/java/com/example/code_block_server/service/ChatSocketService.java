package com.example.code_block_server.service;

import com.example.code_block_server.dto.ClientMessageDTO;

public interface ChatSocketService {

    ClientMessageDTO processMessageDTO(long userId, long chatroomId, String message);
}
