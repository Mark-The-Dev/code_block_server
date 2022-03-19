package com.example.code_block_server.dto;

import lombok.Data;

@Data
public class LoginForm {
    private String email;
    private byte[] password;
}
