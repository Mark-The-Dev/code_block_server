package com.example.code_block_server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Standard DTO sent to the client.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientMessageDTO {
    private String message;
    private String userFrom;
    private String dateSent;
}
