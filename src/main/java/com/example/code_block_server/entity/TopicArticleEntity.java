package com.example.code_block_server.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "topic_article")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TopicArticleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "title")
    private String title;

    @Column(name = "post_body")
    private String postBody;

    @Column(name = "created_at")
    private Date createdAt;

    @ManyToOne
    @JoinColumn(name = "tech_topic_id")
    private TechTopicEntity techTopic;

    @ManyToOne
    @JoinColumn(name = "author")
    private UserEntity user;

}
