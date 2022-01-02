package com.example.code_block_server.repository;

import com.example.code_block_server.entity.ChatRoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatRoomRepository extends JpaRepository<ChatRoomEntity, Long> {

    ChatRoomEntity findById(long id);
}
