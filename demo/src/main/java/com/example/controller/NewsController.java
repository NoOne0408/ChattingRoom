package com.example.controller;

import com.example.common.Result;
import com.example.mapper.UserMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/news")
public class NewsController {
    private UserMapper userMapper;

//    //发布动态
//    @PostMapping("/release")
//    public Result<News> releaseNews(@RequestBody News news) {
//        userMapper.insertNews(news);
//        return Result.success();
//    }
//
//    //删除动态
//    @PostMapping("/deleteNews")
//    public Result<News> deleteNews(@RequestBody Integer newsId) {
//        userMapper.deleteNewsById(newsId);
//        return Result.success();
//    }
//
//    //发布评论
//    @PostMapping("/releaseComment")
//    public Result<Comment> releaseComment(@RequestBody Comment comment){
//        userMapper.insertComment(comment);
//        return Result.success();
//    }

}
