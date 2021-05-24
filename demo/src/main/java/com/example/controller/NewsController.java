package com.example.controller;

import com.example.common.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;

@RestController
@RequestMapping("/news")
public class NewsController {
//    private NewsMapper newsMapper;
//    //发布动态
//    @PostMapping("/releaseNews")
//    public Result<News> releaseNews(@RequestBody News news) {
//        newsMapper.insertNews(news);
//        return Result.success();
//    }
//    //删除动态
//    @PostMapping("/deleteNews")
//    public Result<News> deleteNews(@RequestBody Integer newsId) {
//        newsMapper.deleteNewsById(newsId);
//        return Result.success();
//    }
//    //发布评论
//    @PostMapping("/releaseComment")
//    public Result<Comment> releaseComment(@RequestBody Comment comment){
//        newsMapper.insertComment(comment);
//        return Result.success();
//    }
//    //删除评论
//    @PostMapping("deleteNews")
//    public Result<Comment>deleteComment(@RequestBody  Integer commentId){
//        newsMapper.deleteComment(commentId);
//        return Result.success();
//    }
}
