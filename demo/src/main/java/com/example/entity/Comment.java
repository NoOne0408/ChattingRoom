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
public class Comment {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "userId", nullable = true, length = 20)
    private long userId;

    @Column(name = "newsId", nullable = true, length = 20)
    private long newsId;

    @Column(name = "time", nullable = true, length = 4)
    private int age;

    @Column(name = "content", nullable = true, length = 200)
    private String content;
}