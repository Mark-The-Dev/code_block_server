package com.example.code_block_server;

import com.example.code_block_server.auth.EncryptionUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.security.GeneralSecurityException;

@SpringBootApplication
public class CodeBlockServerApplication {

    public static void main(String[] args) throws GeneralSecurityException, IOException {
        SpringApplication.run(CodeBlockServerApplication.class, args);
        EncryptionUtils.registerEncryptionConfig();
    }
}
