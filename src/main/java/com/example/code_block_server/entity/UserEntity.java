package com.example.code_block_server.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

// name of table
@Entity(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "created_At")
    private ZonedDateTime createdAt;

    @Column(name = "updated_at")
    private ZonedDateTime updatedAt;

    @OneToMany(mappedBy = "user")
    private List<TopicArticleEntity> topicArticleEntities;

    @OneToMany(mappedBy = "user")
    private List<TopicSubscriptionEntity> topicSubscriptionEntities;

    @OneToMany(mappedBy = "userId")
    List<NeighborEntity> neighborEntities;

    @OneToMany(mappedBy = "user")
    List<ChatParticipantEntity> chatParticipantEntities;

    @OneToMany(mappedBy = "user")
    List<ChatMessageEntity> chatMessageEntities;
}
