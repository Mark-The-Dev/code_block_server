package com.example.code_block_server.repository;

import com.example.code_block_server.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findById(long id);

    Optional<UserEntity> findById(String id);

    UserEntity findByEmail(String email);

}
