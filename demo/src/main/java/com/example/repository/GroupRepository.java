package com.example.repository;

import com.example.entity.Group;
import com.example.entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<Group, Long> {
}
