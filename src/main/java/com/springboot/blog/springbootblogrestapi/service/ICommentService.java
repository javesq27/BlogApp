package com.springboot.blog.springbootblogrestapi.service;

import java.util.List;

import com.springboot.blog.springbootblogrestapi.dto.CommentDto;

public interface ICommentService {

    CommentDto create(Long postId, CommentDto commentDto);
    List<CommentDto> getCommentsByPostId(Long postId);
    CommentDto getCommentById(Long postId, Long commentId);
    CommentDto update(Long postId, Long commentId, CommentDto commentRequest);
    void delete(Long postId, Long commentId);
    
}
