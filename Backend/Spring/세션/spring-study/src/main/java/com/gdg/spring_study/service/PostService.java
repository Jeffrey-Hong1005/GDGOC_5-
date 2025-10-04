// package com.gdg.spring_study.service.PostService.java
package com.gdg.spring_study.service;

import com.gdg.spring_study.domain.Post;
import com.gdg.spring_study.dto.PostRequestDto;
import com.gdg.spring_study.dto.PostResponseDto;
import com.gdg.spring_study.repository.PostRepository;
import lombok.RequiredArgsConstructor;
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
                requestDto.author()
        );

        Post savedPost = postRepository.save(post);
        // 서비스 계층에서 직접 엔티티를 DTO로 변환한다.
        return new PostResponseDto(
                savedPost.getId(),
                savedPost.getTitle(),
                savedPost.getContent(),
                savedPost.getAuthor(),
                savedPost.getCreatedDate()
        );
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
                post.getCreatedDate()
        );
    }
}
