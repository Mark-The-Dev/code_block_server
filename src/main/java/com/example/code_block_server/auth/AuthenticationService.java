package com.example.code_block_server.auth;

import com.example.code_block_server.auth.JwtUtils.JWTUtil;
import com.example.code_block_server.dto.AuthPacket;
import com.example.code_block_server.dto.LoginForm;
import com.example.code_block_server.dto.RegisterForm;
import com.example.code_block_server.dto.PublicKeyDTO;
import com.example.code_block_server.entity.UserEntity;
import com.example.code_block_server.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.io.IOException;
import java.security.GeneralSecurityException;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JWTUtil jwtUtil;


    public PublicKeyDTO getPublicKey() throws GeneralSecurityException, IOException {
        return EncryptionUtils.getPublicKey();
    }

    public AuthPacket processLogin(LoginForm loginForm) throws GeneralSecurityException, IOException {
        UserEntity userEntity = userRepository.findByEmail(loginForm.getEmail().toLowerCase());
        return performLogin(userEntity, loginForm);
    }

    public AuthPacket processRegister (RegisterForm registerForm) throws GeneralSecurityException, IOException {
        ZonedDateTime newTime = ZonedDateTime.now( ZoneId.of("UTC+00:00"));
        String hashedPW = BCrypt.hashpw(EncryptionUtils.decrypt(registerForm.getPassword()), BCrypt.gensalt());

        UserEntity newUser = new UserEntity();
        newUser.setFirstName(registerForm.getFirstName());
        newUser.setLastName(registerForm.getLastName());
        newUser.setEmail(registerForm.getEmail().toLowerCase());
        newUser.setPassword(hashedPW);
        newUser.setCreatedAt(newTime);
        newUser.setUpdatedAt(newTime);

        UserEntity userEntity = userRepository.save(newUser);

        LoginForm loginForm = new LoginForm(userEntity.getEmail(), registerForm.getPassword());
        return performLogin(userEntity, loginForm);
    }

    /**
     * Simple method to return the user entity that is attached to the jwt sent to the server.
     * @return current authenticated user from JWT.
     */
    public UserEntity getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userRepository.findByEmail(authentication.getName());
    }

    private AuthPacket performLogin (UserEntity userEntity, LoginForm loginForm) throws GeneralSecurityException, IOException {
        String decryptedPw = EncryptionUtils.decrypt(loginForm.getPassword());
        boolean isValid = BCrypt.checkpw(decryptedPw, userEntity.getPassword());
        if (!isValid) {
            throw new IllegalArgumentException("There was an issue with the user name or password");
        }

        try {
            UsernamePasswordAuthenticationToken authInputToken =
                    new UsernamePasswordAuthenticationToken(loginForm.getEmail(), decryptedPw);

            authenticationManager.authenticate(authInputToken);

            String token = jwtUtil.generateToken(loginForm.getEmail());

            return new AuthPacket(userEntity.getId(), token);
        }catch (AuthenticationException authExc){
            throw new RuntimeException("Invalid Login Credentials");
        }

    }


}
