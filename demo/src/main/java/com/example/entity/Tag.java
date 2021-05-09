package com.example.entity;



import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Tag {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "content", nullable = true, length = 20)
    private String content;

}