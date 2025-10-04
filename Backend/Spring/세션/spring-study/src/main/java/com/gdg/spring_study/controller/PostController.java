// package com.gdg.spring_study.controller.PostController.java
package com.gdg.spring_study.controller;

import com.gdg.spring_study.dto.PostRequestDto;
import com.gdg.spring_study.dto.PostResponseDto;
import com.gdg.spring_study.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/posts")
    public PostResponseDto createPost(@RequestBody PostRequestDto requestDto) {
        return postService.createPost(requestDto);
    }

    @GetMapping("/posts/{id}")
    public PostResponseDto getPostById(@PathVariable Long id) {
        return postService.getPostById(id);
    }
}