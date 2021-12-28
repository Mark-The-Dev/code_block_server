package com.example.code_block_server.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "topic_subscriptions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TopicSubscriptionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;

    @ManyToOne
    @JoinColumn(name = "tech_topic_id")
    private TechTopicEntity techTopic;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

}
