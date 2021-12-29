package com.example.code_block_server.dto;

import lombok.Data;

@Data
public class InboundMessageDTO {
    private String message;
    private long chatRoomId;
    private long userId;
}
