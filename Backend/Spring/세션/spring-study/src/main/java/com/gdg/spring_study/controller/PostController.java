// package com.gdg.spring_study.controller.PostController.java
package com.gdg.spring_study.controller;

import com.gdg.spring_study.dto.PostRequestDto;
import com.gdg.spring_study.dto.PostResponseDto;
import com.gdg.spring_study.service.PostService;
import lombok.RequiredArgsConstructor;
import com.gdg.spring_study.dto.PostUpdateRequestDto;
import org.springframework.http.ResponseEntity;
import java.util.List;

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

    // package com.gdg.spring_study.controller.PostController.java
    @GetMapping("/posts")
    public List<PostResponseDto> getAllPosts() {
        return postService.getAllPosts();
    }

    // package com.gdg.spring_study.controller.PostController.java
    @PutMapping("/posts/{id}")
    public PostResponseDto updatePost(@PathVariable Long id, @RequestBody PostUpdateRequestDto requestDto) {
        return postService.updatePost(id, requestDto);
    }

    

    @DeleteMapping("/posts/{id}")
    public void deletePost(@PathVariable Long id) {
        postService.deletePost(id);
    }
}