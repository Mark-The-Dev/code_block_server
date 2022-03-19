package com.example.code_block_server.auth;

import com.example.code_block_server.dto.AuthPacket;
import com.example.code_block_server.dto.LoginForm;
import com.example.code_block_server.dto.RegisterForm;
import com.example.code_block_server.dto.PublicKeyDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.security.GeneralSecurityException;

@RestController
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @Autowired
    AuthenticationController(
            AuthenticationService authenticationService
    ) {
        this.authenticationService = authenticationService;
    }

    @GetMapping("/public_key")
    public PublicKeyDTO getPublicKey() throws GeneralSecurityException, IOException {
        return authenticationService.getPublicKey();
    }

    @PostMapping("/login")
    public AuthPacket userLogin(@RequestBody LoginForm loginForm) throws GeneralSecurityException, IOException {
        System.out.println("made it");
        return authenticationService.processLogin(loginForm);

    }

    @PostMapping("/register")
    public AuthPacket userRegister(@RequestBody RegisterForm registerForm) {
        return authenticationService.processRegister(registerForm);
    }
}
