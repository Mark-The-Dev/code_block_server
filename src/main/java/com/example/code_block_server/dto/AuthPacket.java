package com.example.code_block_server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthPacket {
    private long userId;
    private String authToken;
}
