// package com.gdg.spring_study.dto.CommentResponseDto.java
public record CommentResponseDto(Long id, String content, String author) {
    public CommentResponseDto(Comment comment) {
        this(comment.getId(), comment.getContent(), comment.getAuthor());
    }
}
