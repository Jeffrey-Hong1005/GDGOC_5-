// package com.gdg.spring_study.service.PostService.java
package com.gdg.spring_study.service;

import com.gdg.spring_study.domain.Post;
import com.gdg.spring_study.dto.PostRequestDto;
import com.gdg.spring_study.dto.PostResponseDto;
import com.gdg.spring_study.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import main.java.com.gdg.spring_study.dto.PostUpdateRequestDto;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostService {

        private final PostRepository postRepository;

        @Transactional
        public PostResponseDto createPost(PostRequestDto requestDto) {

                Post post = new Post(
                                requestDto.title(),
                                requestDto.content(),
                                requestDto.author());

                Post savedPost = postRepository.save(post);

                return new PostResponseDto(
                                savedPost.getId(),
                                savedPost.getTitle(),
                                savedPost.getContent(),
                                savedPost.getAuthor(),
                                savedPost.getCreatedDate());
        }

        @Transactional(readOnly = true)
        public PostResponseDto getPostById(Long id) {
                Post post = postRepository.findById(id)
                                .orElseThrow(() -> new IllegalArgumentException("ID에 해당하는 게시글을 찾을 수 없습니다: " + id));

                // 서비스 계층에서 직접 엔티티를 DTO로 변환한다.
                return new PostResponseDto(
                                post.getId(),
                                post.getTitle(),
                                post.getContent(),
                                post.getAuthor(),
                                post.getCreatedDate());
        }

        @Transactional(readOnly = true)
        public List<PostResponseDto> getAllPosts() {
                return postRepository.findAll().stream()
                                .map(PostResponseDto::new) // post -> new PostResponseDto(post)
                                .collect(Collectors.toList());
        }

        // package com.gdg.spring_study.service.PostService.java
        @Transactional
        public PostResponseDto updatePost(Long id, PostUpdateRequestDto requestDto) {
                Post post = postRepository.findById(id)
                                .orElseThrow(() -> new IllegalArgumentException("ID에 해당하는 게시글을 찾을 수 없습니다: " + id));

                post.update(requestDto.title(), requestDto.content());

                return new PostResponseDto(post);
        }

        // package com.gdg.spring_study.service.PostService.java
        @Transactional
        public void deletePost(Long id) {
                Post post = postRepository.findById(id)
                                .orElseThrow(() -> new IllegalArgumentException("ID에 해당하는 게시글을 찾을 수 없습니다: " + id));
                postRepository.delete(post);
        }
}
