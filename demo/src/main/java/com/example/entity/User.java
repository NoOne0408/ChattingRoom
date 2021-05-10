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
public class User {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "username", nullable = true, length = 20)
    private String username;

    @Column(name = "password", nullable = true, length = 20)
    private String password;

    @Column(name = "icon", nullable = true, length = 20)
    private String icon;

    @Column(name = "email", nullable = true, length = 20)
    private String email;

    @Column(name = "nickname", nullable = true, length = 20)
    private String nickname;

    @Column(name = "onlineFlag", nullable = true, length = 4)
    private Integer onlineFlag;
}
/*
public class User implements Serializable {
    private Integer id;

    private String username;

    private String nickname;

    private String password;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
}*/