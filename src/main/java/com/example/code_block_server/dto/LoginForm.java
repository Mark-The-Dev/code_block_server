package com.example.code_block_server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class LoginForm {
    private String email;
    private String password;
}
