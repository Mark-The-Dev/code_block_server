package com.example.code_block_server.auth;

import com.example.code_block_server.dto.AuthPacket;
import com.example.code_block_server.dto.LoginForm;
import com.example.code_block_server.dto.RegisterForm;
import com.example.code_block_server.dto.PublicKeyDTO;
import com.example.code_block_server.entity.UserEntity;
import com.example.code_block_server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.io.IOException;
import java.security.GeneralSecurityException;

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

    public PublicKeyDTO getPublicKey() throws GeneralSecurityException, IOException {
        return EncryptionUtils.getPublicKey();
    }

    public AuthPacket processLogin(LoginForm loginForm) {
        UserEntity userEntity = userRepository.findByEmail(loginForm.getEmail().toLowerCase());
        return performLogin(userEntity, loginForm.getPassword());
    }

    public AuthPacket processRegister (RegisterForm registerForm) {
        ZonedDateTime newTime = ZonedDateTime.now( ZoneId.of("UTC+00:00"));
        String hashedPW = BCrypt.hashpw(registerForm.getPassword(), BCrypt.gensalt());

        UserEntity newUser = new UserEntity();
        newUser.setFirstName(registerForm.getFirstName());
        newUser.setLastName(registerForm.getLastName());
        newUser.setEmail(registerForm.getEmail().toLowerCase());
        newUser.setPassword(hashedPW);
        newUser.setCreatedAt(newTime);
        newUser.setUpdatedAt(newTime);

        UserEntity userEntity = userRepository.save(newUser);
        return performLogin(userEntity, registerForm.getPassword());
    }

    public void verifyAuthenticatedUser(long userId, String jwt) {
        JwtUtils.verifyJwtString(jwt, String.valueOf(userId));
    }

    private AuthPacket performLogin (UserEntity userEntity, String password) {
        boolean isValid = BCrypt.checkpw(password, userEntity.getPassword());
        if (!isValid) {
            throw new IllegalArgumentException("There was an issue with the user name or password");
        }
        long userId = userEntity.getId();
        String authToken = generateJwtString(String.valueOf(userId));

        return new AuthPacket(userId, authToken);
    }


}
