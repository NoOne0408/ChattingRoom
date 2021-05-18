package com.example.repository;

import com.example.entity.Record;
import com.example.entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecordRepository extends JpaRepository<Record, Long> {
    List<Record> findByUserIdAndTalkIdAndTalkType(Long userId, Long talkId, String talkType);
}
