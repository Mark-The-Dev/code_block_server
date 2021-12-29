package com.example.code_block_server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OutboundMessageDTO {
    private String message;
    private String userFrom;
    private String dateSent;
}
