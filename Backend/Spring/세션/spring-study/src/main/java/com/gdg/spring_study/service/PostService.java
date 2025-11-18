// package com.gdg.spring_study.service.PostService.java
package com.gdg.spring_study.service;

import com.gdg.spring_study.domain.Post;
import com.gdg.spring_study.dto.PostRequestDto;
import com.gdg.spring_study.dto.PostResponseDto;
import com.gdg.spring_study.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import com.gdg.spring_study.dto.PostUpdateRequestDto;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostService {

        private final PostRepository postRepository;
        //하나의 단위로 묶어서 처리를 한다
        @Transactional
        public PostResponseDto createPost(PostRequestDto requestDto) {

                Post post = new Post(
                                requestDto.title(),
                                requestDto.content(),
                                requestDto.author());

                Post savedPost = postRepository.save(post); //DB에 저장 - 서비스에서 레포지토리를 호출 , 3가지 부분을 하나로 묶겠다

                return new PostResponseDto(
                                savedPost.getId(),
                                savedPost.getTitle(),
                                savedPost.getContent(),
                                savedPost.getAuthor(),
                                savedPost.getCreatedDate(),
                        savedPost.getLikeCount());
        }

        @Transactional(readOnly = true) // 수정 안되고 읽기만 한다
        public PostResponseDto getPostById(Long id) {
                Post post = postRepository.findById(id)
                                .orElseThrow(() -> new IllegalArgumentException("ID에 해당하는 게시글을 찾을 수 없습니다: " + id));

                // 서비스 계층에서 직접 엔티티를 DTO로 변환한다.
                return new PostResponseDto(
                                post.getId(),
                                post.getTitle(),
                                post.getContent(),
                                post.getAuthor(),
                                post.getCreatedDate(),
                                post.getLikeCount());


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

        @Transactional
        public PostResponseDto likePost(Long id) {
                Post post = postRepository.findById(id)
                                .orElseThrow(() -> new IllegalArgumentException("ID에 해당하는 게시글을 찾을 수 없습니다: " + id));

                Post savedPost = post.addLike();
                Post savedPost1 = postRepository.save(savedPost);
                return new PostResponseDto(savedPost1);
        }
}
