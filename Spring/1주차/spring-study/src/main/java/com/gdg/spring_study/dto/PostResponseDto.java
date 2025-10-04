// package com.gdg.spring_study.dto.PostResponseDto.java
package com.gdg.spring_study.dto;

import java.time.LocalDateTime;

public record PostResponseDto(
        Long id,
        String title,
        String content,
        String author,
        LocalDateTime createdDate
) {
}
