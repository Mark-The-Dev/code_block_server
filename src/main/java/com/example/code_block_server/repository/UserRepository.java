package com.example.code_block_server.repository;

import com.example.code_block_server.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findById(long id);
}
