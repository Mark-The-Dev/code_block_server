package com.example.code_block_server.dto;

import lombok.Data;

/**
 * Standard Server received DTO
 */
@Data
public class ServerMessageDTO {
    private String message;
    private long chatRoomId;
    private long userId;
}
