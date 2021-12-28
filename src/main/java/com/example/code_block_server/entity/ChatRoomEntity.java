package com.example.code_block_server.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity(name = "chat_rooms")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChatRoomEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "room_name")
    private String roomName;

    @Column(name = "created_at")
    private Date createdAt;

    @OneToMany(mappedBy = "chatRoom")
    private List<ChatParticipantEntity> chatParticipantEntities;

    @OneToMany(mappedBy = "chatRoom")
    private List<ChatMessageEntity> chatMessageEntities;
}
