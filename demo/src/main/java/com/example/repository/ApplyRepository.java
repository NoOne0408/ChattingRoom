package com.example.repository;

import com.example.entity.Apply;
import com.example.entity.ChatGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplyRepository  extends JpaRepository<Apply, Long> {
    List<Apply> findByAppliedIdAndResultFlagAndType(Long appliedId,Integer resultFlag,String type);
}
