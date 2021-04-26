package com.example.controller;

import com.example.common.Result;
import com.example.entity.User;
import com.example.mapper.UserMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;

/**
 * 关注公众号：Java学习指南
 * 后台回复：登录  获取源码
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserMapper userMapper;

    //登录逻辑，获取前端传来的参数，如果姓名和密码正确则返回成功
    @PostMapping("/login")
    public Result<User> login(@RequestBody User user) {
        if (!checkParam(user)) {//如果有参数未填写，返回错误
            return Result.error("-1", "缺少必要参数");
        }
        User dbUser = userMapper.selectByUsernameAndPassword(user);
        if (dbUser == null) {//如果通过账号和密码无法找到对应的用户
            return Result.error("-1", "账号或密码错误");
        }
        //如果可以查到对应的用户则返回成功
        return Result.success(dbUser);
    }

    public ArrayList<User> myFriends(@RequestBody Integer myid){
        ArrayList<User> myfriends=new ArrayList<>();
        return myfriends;
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
