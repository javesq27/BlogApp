package com.springboot.blog.springbootblogrestapi.repository;

import java.util.List;

import com.springboot.blog.springbootblogrestapi.entity.Comment;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByPostId(Long postId);
    
}
