package com.example.repository;

import com.example.entity.Person;
import com.example.entity.User;
import com.example.entity.UserTag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserTagRepository extends JpaRepository<UserTag, Long> {
    List<UserTag> findByUserId(Long userId);
}
