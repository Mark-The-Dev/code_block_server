package com.example.code_block_server.dto;

import lombok.Data;

@Data
public class RegisterForm {
    String email;
    String password;
    String firstName;
    String lastName;
}
