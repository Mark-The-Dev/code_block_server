package com.example.code_block_server.auth;

import com.example.code_block_server.entity.UserEntity;
import com.example.code_block_server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    public String processLogin(String email, String password) {

        UserEntity userEntity = userRepository.findByEmail(email);

        if (userEntity == null ||!password.equals(userEntity.getPassword())) {
            throw new IllegalArgumentException("There was an issue with the user name or password");
        }

        return generateJwtString(String.valueOf(userEntity.getId()));
    }

}
