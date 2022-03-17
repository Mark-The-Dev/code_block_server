package com.example.code_block_server.auth;

import com.example.code_block_server.dto.AuthPacket;
import com.example.code_block_server.dto.LoginForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @Autowired
    AuthenticationController(
            AuthenticationService authenticationService
    ) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    public AuthPacket userLogin(@RequestBody LoginForm loginForm){
        return authenticationService.processLogin(loginForm);

    }
}
