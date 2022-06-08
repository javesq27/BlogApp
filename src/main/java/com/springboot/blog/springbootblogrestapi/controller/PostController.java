package com.springboot.blog.springbootblogrestapi.controller;


import javax.validation.Valid;

import com.springboot.blog.springbootblogrestapi.dto.PostDto;
import com.springboot.blog.springbootblogrestapi.page.PostResponse;
import com.springboot.blog.springbootblogrestapi.service.PostService;
import com.springboot.blog.springbootblogrestapi.utils.PageUtils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<PostDto> create(@Valid @RequestBody PostDto postDto) {

        return new ResponseEntity<>(postService.create(postDto), HttpStatus.CREATED); 
    }

    @GetMapping
    public PostResponse getAll(@RequestParam(value="pageNo", defaultValue= PageUtils.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
                                @RequestParam(value = "pageSize", defaultValue = PageUtils.DEFAULT_PAGE_SIZE, required = false) int pageSize,
                                @RequestParam(value = "sortBy", defaultValue = PageUtils.DEFAULT_SORT_BY, required = false) String sortBy) {
        
        return postService.getAll(pageNo, pageSize, sortBy);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getById(@PathVariable(name ="id") Long id) {

        return ResponseEntity.ok(postService.findById(id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<PostDto> update(@Valid @RequestBody PostDto postDto, @PathVariable(name="id") Long id) {
        
        PostDto postResponse = postService.update(postDto, id);
        return new ResponseEntity<>(postResponse, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable(name="id") Long id) {

        postService.delete(id);
        return new ResponseEntity<>("Post deleted successfully", HttpStatus.OK);
    }
    
}
