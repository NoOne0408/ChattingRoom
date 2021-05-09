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
public class Friendship {

    @Id
    @GeneratedValue
    private Long id;


    @Column(name = "userA", nullable = true, length = 4)
    private Long userA;

    @Column(name = "userB", nullable = true, length = 4)
    private Long userB;
}