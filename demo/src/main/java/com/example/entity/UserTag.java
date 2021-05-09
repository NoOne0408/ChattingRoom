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
public class UserTag {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "userId", nullable = true, length = 4)
    private Long userId;

    @Column(name = "tagId", nullable = true, length = 4)
    private Long tagId;
}