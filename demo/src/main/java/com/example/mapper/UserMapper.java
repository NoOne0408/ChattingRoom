package com.example.mapper;

import com.example.entity.*;
import com.example.repository.*;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserMapper {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private FriendshipRepository friendshipRepository;
    @Autowired
    private UserTagRepository userTagRepository;
    @Autowired
    private TopicRepository topicRepository;

    public User selectByUsernameAndPassword(User user) {
        User u = userRepository.findByUsernameAndPassword(user.getUsername(),user.getPassword());
        return u;
    }
    public ArrayList<User> selectFriendsById(int id){
        List<Friendship> fA = friendshipRepository.findByUserA((long) id);
        List<Friendship> fB = friendshipRepository.findByUserB((long) id);
        
        ArrayList<User> result= new ArrayList<>();
        for(Friendship f : fA){
            if (userRepository.findById(f.getUserB()).isPresent())
                result.add(userRepository.findById(f.getUserB()).get());
        }
        for(Friendship f : fB){
            if (userRepository.findById(f.getUserA()).isPresent())
                result.add(userRepository.findById(f.getUserA()).get());
        }
        return result;
    }
    public User selectUserById(int index){
        User u = new User();
        if(userRepository.findById((long)index).isPresent())
            u = userRepository.findById((long)index).get();
        return u;
    }
    public ArrayList<Integer> selectHobbyById(int id){
        List<UserTag> l = userTagRepository.findByUserId((long) id);
        ArrayList<Integer> result = new ArrayList<>();
        for(UserTag ut : l){
            result.add(ut.getTagId().intValue());
        }
        return result;
    }
    public Topic selectTopic(int topicid){
        Topic t = new Topic();
        if(topicRepository.findById(Long.valueOf(topicid)).isPresent())
            return topicRepository.findById(Long.valueOf(topicid)).get();
        else
            return t;
    }
    public void insertSelective(User user){
        userRepository.saveAndFlush(user);
        return;
    }
    public void deleteByName(String username){
        User u = userRepository.findByUsername(username    );
        userRepository.delete(u);
        return;
    }
    public void updateByPrimaryKey(User user){
        userRepository.deleteById(user.getId());
        userRepository.saveAndFlush(user);
        return;
    }
/*    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    List<User> selectAll();

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User selectByUsernameAndPassword(User user);*/
}