// package com.gdg.spring_study.dto.PostRequestDto.java
package com.gdg.spring_study.dto;

public record PostRequestDto(
        String title,
        String content,
        String author
) {
}
