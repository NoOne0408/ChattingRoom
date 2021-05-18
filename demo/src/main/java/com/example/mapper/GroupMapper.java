package com.example.mapper;

import com.example.entity.*;
import com.example.repository.*;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GroupMapper{
    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private GroupUserRepository groupUserRepository;
    public Group createGroup(Long userId,String groupName){
        Group g = new Group();
        g.setName(groupName);
        groupRepository.saveAndFlush(g);
        GroupUser gu = new GroupUser();
        gu.setGroupId(g.getId());
        gu.setUserId(userId);
        groupUserRepository.saveAndFlush(gu);
        return g;
    }
}