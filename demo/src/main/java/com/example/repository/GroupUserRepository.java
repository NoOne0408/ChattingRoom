package com.example.repository;

import com.example.entity.GroupUser;
import com.example.entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GroupUserRepository extends JpaRepository<GroupUser, Long> {
    List<GroupUser> findByGroupId(Long groupId);
    List<GroupUser> findByUserId(Long userId);
}
