package com.example.repository;

import com.example.entity.Comment;
import com.example.entity.Friendship;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
