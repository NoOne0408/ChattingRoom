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
public class GroupUser {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "groupId", nullable = true, length = 4)
    private Long groupId;

    @Column(name = "userId", nullable = true, length = 4)
    private Long userId;
}