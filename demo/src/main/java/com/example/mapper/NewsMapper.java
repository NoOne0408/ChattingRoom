package com.example.mapper;

import com.example.entity.Comment;
import com.example.entity.News;
import com.example.repository.CommentRepository;
import com.example.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NewsMapper {
    @Autowired
    private NewsRepository newsRepository;
    @Autowired
    private CommentRepository commentRepository;

    public News insertNews(News n){
        newsRepository.saveAndFlush(n);
        return n;
    }

    public void deleteNewsById(Long id){
        newsRepository.deleteById(id);
        return;
    }

    public Comment insertComment(Comment c){
        commentRepository.saveAndFlush(c);
        return c;
    }

    public void deleteComment(Long id){
        commentRepository.deleteById(id);
        return;
    }
}
