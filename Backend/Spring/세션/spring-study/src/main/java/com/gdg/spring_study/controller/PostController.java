// package com.gdg.spring_study.controller.PostController.java
package com.gdg.spring_study.controller;

import com.gdg.spring_study.dto.PostRequestDto;
import com.gdg.spring_study.dto.PostResponseDto;
import com.gdg.spring_study.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import com.gdg.spring_study.dto.PostUpdateRequestDto;

import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;
import java.util.List;

import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    //이 부분이 컨트롤러에서 서비스넘어가는 과정
    @PostMapping("/posts")
    public PostResponseDto createPost(@RequestBody @Valid PostRequestDto requestDto) {
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

    @PutMapping("/posts/{id}/like")
    public ResponseEntity<PostResponseDto> addLike(@PathVariable Long id) {
        PostResponseDto updatedPost = postService.likePost(id);
        return ResponseEntity.ok(updatedPost);
    }
}