package com.springboot.blog.springbootblogrestapi.page;

import java.util.List;

import com.springboot.blog.springbootblogrestapi.dto.PostDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostResponse {
    
    private int pageNo;
    private int pageSize;
    private Long totalElements;
    private int totalPages;
    private boolean last;
    private List<PostDto> content;
}
