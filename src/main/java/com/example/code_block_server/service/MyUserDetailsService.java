package com.example.code_block_server.service;

import com.example.code_block_server.entity.UserEntity;
import com.example.code_block_server.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {

    private final UserRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity userRes = userRepo.findByEmail(email);
        if(userRes == null)
            throw new UsernameNotFoundException("Could not findUser with email = " + email);
        return new org.springframework.security.core.userdetails.User(
                email,
                userRes.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));
    }
}
