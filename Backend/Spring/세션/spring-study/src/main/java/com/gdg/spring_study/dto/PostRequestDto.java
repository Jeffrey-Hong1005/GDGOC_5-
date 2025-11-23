// package com.gdg.spring_study.dto.PostRequestDto.java
package com.gdg.spring_study.dto;

import jakarta.validation.constraints.*;

public record PostRequestDto(

        @NotBlank(message = "제목은 필수입니다.") // 빈 문자열 "", 공백 " " 불가능
        @Size(max = 20, message = "제목은 20자 이내여야 합니다.")
        String title,

        @NotBlank(message = "내용은 필수입니다.")
        // @Size(min = 10) // (선택) 내용은 최소 10자 이상이어야 한다면 추가
        String content,

        @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "작성자는 영문과 숫자만 가능합니다.") // 정규식 검사
        @Size(min = 3, max = 10, message = "작성자 이름은 3~10자여야 합니다.")
        String author
) {
}