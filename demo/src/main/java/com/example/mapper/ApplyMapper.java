package com.example.mapper;

import com.example.entity.Apply;
import com.example.entity.ChatGroup;
import com.example.repository.ApplyRepository;
import com.example.repository.ChatGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ApplyMapper {
    @Autowired
    private ApplyRepository applyRepository;
    @Autowired
    private ChatGroupRepository chatGroupRepository;

    public List<Apply> selectFriendApply(Long appliedId){
       List<Apply> result =  applyRepository.findByAppliedIdAndResultFlagAndType(appliedId,0,"Friend");
       return result;
    }

    public List<Apply> selectGroupApply(Long userId){
        List<ChatGroup> groups = chatGroupRepository.findByMasterUserId(userId);
        List<Apply> result = new ArrayList<>();
        for (ChatGroup g : groups){
            List<Apply> selectResult =  applyRepository.findByAppliedIdAndResultFlagAndType(g.getId(),0,"Group");
            for (Apply a :selectResult){
                result.add(a);
            }
        }
        return result;
    }
}
