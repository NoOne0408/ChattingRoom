package com.example.repository;

import com.example.entity.Friendship;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FriendshipRepository extends JpaRepository<Friendship, Long> {
    List<Friendship> findByUserA(Long userA);
    List<Friendship> findByUserB(Long userB);
}
