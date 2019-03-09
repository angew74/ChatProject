package com.deltasi.chat.model;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.hibernate.annotations.NaturalId;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "chatmessages")
public class ChatMessage {


    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Id
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    @NaturalId
    private MessageType type;


    @Column(name = "content")
    private String content;

    @Column(name = "sender")
    private String sender;

    @Column(name = "createddate")
    private LocalDateTime createDate;

    @ManyToOne(fetch = FetchType.LAZY)
   /* @JoinTable(name = "users",
            joinColumns = @JoinColumn(name = "user_id"))*/
    @JoinColumn(name="userid",referencedColumnName="user_id", insertable=false, updatable=false)
    @JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id")
    @JsonIgnore
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
   /* @JoinTable(name = "topics",
            joinColumns = @JoinColumn(name = "topic_id", insertable=false, updatable=false))*/
    @JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id")
    @JoinColumn(name="topicid",referencedColumnName="id", insertable=false, updatable=false)
    private Topic topic;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }


    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }
}
