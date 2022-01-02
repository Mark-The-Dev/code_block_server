package com.example.code_block_server.repository;

import com.example.code_block_server.entity.ChatMessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * This is the repository responsible for handling chat messages
 *
 * This extends JPA Repository to give us simple functional queries and useful methods.
 */
@Repository
public interface ChatMessageRepository extends JpaRepository<ChatMessageEntity, Long> {

    // TODO: Add quires to grab messages based on arguments
    ChatMessageEntity findById(long id);
}
