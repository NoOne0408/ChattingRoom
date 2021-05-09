package com.example.repository;

import com.example.entity.Tag;
import com.example.entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Long> {
}
