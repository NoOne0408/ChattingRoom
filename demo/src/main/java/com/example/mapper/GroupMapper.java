package com.example.mapper;

import com.example.entity.*;
import com.example.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GroupMapper{
    @Autowired
    private ChatGroupRepository chatGroupRepository;
    @Autowired
    private GroupUserRepository groupUserRepository;
    public ChatGroup createGroup(Long userId, String groupName){
        ChatGroup g = new ChatGroup();
        g.setName(groupName);
        chatGroupRepository.saveAndFlush(g);
        GroupUser gu = new GroupUser();
        gu.setGroupId(g.getId());
        gu.setUserId(userId);
        groupUserRepository.saveAndFlush(gu);
        return g;
    }
}