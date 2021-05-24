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
public class Apply {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "userId", nullable = true, length = 20)
    private long userId;

    @Column(name = "appliedId", nullable = true, length = 20)
    private long appliedId;

    @Column(name = "time", nullable = true, length = 4)
    private Date time;

    @Column(name = "type", nullable = true, length = 200)
    private String type;

    @Column(name = "content", nullable = true, length = 200)
    private String content;

    @Column(name = "resultFlag", nullable = true, length = 4)
    private Integer resultFlag;

    @Column(name = "result", nullable = true, length = 20)
    private String result;

    @Column(name = "resultTime", nullable = true, length = 4)
    private Date resultTime;
}