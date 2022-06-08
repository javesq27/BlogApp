package com.springboot.blog.springbootblogrestapi.service;

import com.springboot.blog.springbootblogrestapi.dto.PostDto;
import com.springboot.blog.springbootblogrestapi.page.PostResponse;

public interface IPostService {

    PostDto create(PostDto postDto);
    PostResponse getAll(int pageNo, int pageSize, String sortBy);
    PostDto findById(Long id);
    PostDto update(PostDto postDto, Long id);
    void delete(Long id);
}
