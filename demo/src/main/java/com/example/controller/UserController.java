package com.example.controller;

import com.example.common.Result;
import com.example.POJO.*;
import com.example.entity.Topic;
import com.example.entity.User;
import com.example.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.socket.WebSocketMessage;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;


@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserMapper userMapper;


    //登录逻辑，前端传来username和password，验证身份
    @PostMapping("/login")
    public Result<User> login(@RequestBody User user) {
        if (!checkParam(user)) {
            return Result.error("-1", "缺少必要参数");
        }
        User dbUser = userMapper.selectByUsernameAndPassword(user);
        if (dbUser == null) {
            return Result.error("-1", "账号或密码错误");
        }

        //测试打印全部好友和匿名话题匹配
//        ArrayList<User> myfri=myFriends(1);
//        for(int i=0;i<myfri.size();i++){
//            System.out.println(myfri.get(i).getEmail());
//            int myid=Integer.parseInt(String.valueOf(dbUser.getId()));
//            int yourid=Integer.parseInt(String.valueOf(myfri.get(i).getId()));
//            Topic topic=ourTopic(myid,yourid);
//        }

//        //测试匿名好友匹配
//        ArrayList<Integer> userList=new ArrayList<Integer>();
//        userList.add(2);
//        userList.add(3);
//        userList.add(4);
//        int myid=Integer.parseInt(String.valueOf(dbUser.getId()));
//        User fitFri=anonymousUser(myid,userList);
//        System.out.println(fitFri.getUsername());

        return Result.success(dbUser);
    }

    //获取当前用户所有好友
    public ArrayList<User> myFriends(@RequestBody Integer myid){
        ArrayList<User> myfriends=new ArrayList<>();
        //获取当前用户的所有好友信息
        myfriends=userMapper.selectFriendsById(myid);
        return myfriends;
    }

    //匿名用户匹配
    public User anonymousUser(@RequestBody Integer myid,ArrayList<Integer> freeUsers){
        User auser=new User();
        int[] similarityArray = new int[freeUsers.size()];//存放相似度
        int[] userIdArray = new int[freeUsers.size()];//存放相似度对应的用户id

        ArrayList<Integer> myhobbylist=new ArrayList<Integer>();
        ArrayList<Integer> userhobbylist=new ArrayList<Integer>();

        myhobbylist=userMapper.selectHobbyById(myid);//获取我的爱好列表

        //获取所有空闲可匹配用户的爱好详情信息
        for(int i=0;i<freeUsers.size();i++){
            userhobbylist=userMapper.selectHobbyById(freeUsers.get(i));//获取好友爱好列表

            //为了匹配更精确，以爱好多的一方为匹配对象
            if(myhobbylist.size()>=userhobbylist.size()){
                int similarity=hobbySimilarity(myhobbylist,userhobbylist);
                System.out.println("similarity"+similarity);
                similarityArray[i]=similarity;
                userIdArray[i]=freeUsers.get(i);
            }
            else if(myhobbylist.size()<userhobbylist.size()){
                int similarity=mutualHobby(userhobbylist,myhobbylist);
                System.out.println("similarity"+similarity);
                similarityArray[i]=similarity;
                userIdArray[i]=freeUsers.get(i);
            }
        }

        int maxIndex=findMaxIndex(similarityArray);//返回相似度最高的用户的id所在位置
        int fitUserId=userIdArray[maxIndex];//返回相似度最高的用户id
        System.out.println("fitUserId:"+fitUserId);

        auser=userMapper.selectUserById(fitUserId);//查询好友并且返回
        System.out.println("auser"+auser.getUsername());
        return (auser);
    }

    public int findMaxIndex(int[] array){
        int max = 0;
        int maxIndex=0;
        for (int i = 0; i < array.length; i++) {
            if(max < array[i]){
                max = array[i];
                maxIndex=i;
            }
        }
        return maxIndex;
    }

    //计算两好友之间的兴趣相似度
    public int hobbySimilarity(ArrayList<Integer> hobbylist1, ArrayList<Integer> hobbylist2){
        int similarity=0;
        for(int i=0;i<hobbylist1.size()&&i<hobbylist2.size();i++){
            int hobbid=hobbylist2.get(i);
            if(hobbylist1.contains(hobbid)){
                similarity++;
            }
        }
        return similarity;
    }

    //匿名话题匹配
    public Topic ourTopic(@RequestBody Integer id1,Integer id2){
        Topic topic=new Topic();
        //从用户标签表中获取用户1的兴趣标签列
        ArrayList<Integer> hobbylist1=new ArrayList<>();
        hobbylist1=userMapper.selectHobbyById(id1);
        //从用户标签表中获取用户2的兴趣标签列
        ArrayList<Integer> hobbylist2=new ArrayList<>();
        hobbylist2=userMapper.selectHobbyById(id2);

        int topicId=0;
        //将数据较多的作为被比较的列表，避免找不到共同话题
        if(hobbylist1.size()>=hobbylist2.size()){
            topicId=mutualHobby(hobbylist1,hobbylist2);
        }
        else if(hobbylist1.size()<hobbylist2.size()){
            topicId=mutualHobby(hobbylist2,hobbylist1);
        }
        System.out.println("相似话题"+topicId);

        //根据话题类型选择一个合适的话题
        topic=userMapper.selectTopic(topicId);
        return topic;
    }

    //获取两个hobbylist中重复的话题
    private int mutualHobby(ArrayList<Integer> hobbylist1, ArrayList<Integer> hobbylist2) {
        int topicId = 0;
        for(int i=0;i<hobbylist1.size()&&i<hobbylist2.size();i++){
            int hobbid=hobbylist2.get(i);
            if(hobbylist1.contains(hobbid)){
                topicId=hobbid;
                break;
            }
        }
        return topicId;
    }


    //注册逻辑，获取前端传来的数据，新注册一个用户，获取姓名昵称和密码
    @PostMapping("/register")
    public Result<User>register(@RequestBody User user){
        if (!checkParam(user)) {//如果有参数未填写，返回错误
            return Result.error("-1", "注册新用户请填写完整的用户信息");
        }
        userMapper.insertSelective(user);
        return Result.success(user);
    }

    //删除逻辑，获取前端传来的username，删除该用户信息
    @PostMapping("/deleteUser")
    public Result<User>deleteUser(@RequestBody User user){
        userMapper.deleteByName(user.getUsername());
        return  Result.success();
    }

    //修改逻辑，获取前端username、nickname、password以修改用户信息
    @PostMapping("/updateInfo")
    public Result<User>updateInfo(@RequestBody User user){
        userMapper.updateByPrimaryKey(user);
        return  Result.success();
    }


    private boolean checkParam(User user) {
        return user.getUsername() != null && user.getPassword() != null;
    }
}
