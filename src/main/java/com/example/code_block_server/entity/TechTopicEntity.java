package com.example.code_block_server.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity(name = "tech_topics")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TechTopicEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "topic_name")
    private String topicName;

    @Column(name = "topic_info")
    private String topicInfo;

    @Column(name = "created_at")
    private Date createdAt;

    @OneToMany(mappedBy = "techTopic")
    private List<TopicArticleEntity> topicArticleEntities;

    @OneToMany(mappedBy = "techTopic")
    private List<TopicSubscriptionEntity> topicSubscriptionEntities;
}
