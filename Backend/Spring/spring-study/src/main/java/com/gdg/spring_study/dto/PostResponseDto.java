// package com.gdg.spring_study.dto.PostResponseDto.java
package com.gdg.spring_study.dto;
import com.gdg.spring_study.domain.Post;
import java.time.LocalDateTime;

public record PostResponseDto(
        Long id,
        String title,
        String content,
        String author,
        LocalDateTime createdDate
) {
        public PostResponseDto(Post post) { 
           this(
                post.getId(),
                post.getTitle(),
                post.getContent(),
                post.getAuthor(),
                post.getCreatedDate()
        );
    }

}
