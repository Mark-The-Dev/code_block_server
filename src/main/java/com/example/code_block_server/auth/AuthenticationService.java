package com.example.code_block_server.auth;

import com.example.code_block_server.dto.AuthPacket;
import com.example.code_block_server.dto.LoginForm;
import com.example.code_block_server.entity.UserEntity;
import com.example.code_block_server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import static com.example.code_block_server.auth.JwtUtils.generateJwtString;

@Service
public class AuthenticationService {
    private final UserRepository userRepository;

    @Autowired
    AuthenticationService(
            UserRepository userRepository
    ) {
        this.userRepository = userRepository;
    }

    public AuthPacket processLogin(LoginForm loginForm) {
        UserEntity userEntity = userRepository.findByEmail(loginForm.getEmail());

        boolean isValid = BCrypt.checkpw(loginForm.getPassword(), userEntity.getPassword());
        if (userEntity == null || !isValid) {
            throw new IllegalArgumentException("There was an issue with the user name or password");
        }
        long userId = userEntity.getId();
        String authToken = generateJwtString(String.valueOf(userId));

        return new AuthPacket(userId, authToken);
    }

}
