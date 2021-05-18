package com.example.entity;



import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;
@Entity
@Getter
@Setter
public class Record {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "userId", nullable = true, length = 4)
    private Long userId;

    @Column(name = "talkId", nullable = true, length = 4)
    private Long talkId;
    
    @Column(name = "talkType", nullable = true, length = 20)
    private String talkType;
    
    @Column(name = "recordType", nullable = true, length = 20)
    private String recordType;
    
    @Column(name = "time", nullable = true)
    private Date time;
    
    @Column(name = "content", nullable = true, length = 200)
    private String content;
}