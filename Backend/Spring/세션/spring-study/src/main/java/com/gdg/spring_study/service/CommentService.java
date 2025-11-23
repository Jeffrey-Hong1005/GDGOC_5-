package com.gdg.spring_study.service;

import com.gdg.spring_study.domain.Comment;
import com.gdg.spring_study.domain.Post;
import com.gdg.spring_study.dto.CommentRequestDto;
import com.gdg.spring_study.dto.CommentResponseDto;
import com.gdg.spring_study.repository.CommentRepository;
import com.gdg.spring_study.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

// package com.gdg.spring_study.service.CommentService.java
@Service
@RequiredArgsConstructor
public class CommentService {

	private final CommentRepository commentRepository;
	private final PostRepository postRepository;

	@Transactional
	public CommentResponseDto createComment(Long postId, CommentRequestDto requestDto) {
		Post post = postRepository.findById(postId)
			.orElseThrow(() -> new IllegalArgumentException("ID에 해당하는 게시글을 찾을 수 없습니다:"));

		Comment comment = new Comment(requestDto.content(), requestDto.author(), post);
		Comment savedComment = commentRepository.save(comment);
		return new CommentResponseDto(savedComment);
	}

	@Transactional(readOnly = true)
	public List<CommentResponseDto> getCommentsByPostId(Long postId) {
		Post post = postRepository.findById(postId)
			.orElseThrow(() -> new IllegalArgumentException("ID에 해당하는 게시글을 찾을 수 없습니다:"));

		List<Comment> comments = commentRepository.findAllByPost(post);
		return comments.stream().map(CommentResponseDto::new).collect(Collectors.toList());
	}

	@Transactional
	public CommentResponseDto updateComment(Long commentId, CommentRequestDto requestDto) {
		Comment comment = commentRepository.findById(commentId)
			.orElseThrow(() -> new IllegalArgumentException("ID에 해당하는 댓글을 찾을 수 없습니다:"));

		comment.update(requestDto.content(), requestDto.author());
		return new CommentResponseDto(comment);
	}

	@Transactional
	public void deleteComment(Long commentId) {
		Comment comment = commentRepository.findById(commentId)
			.orElseThrow(() -> new IllegalArgumentException("ID에 해당하는 댓글을 찾을 수 없습니다:"));
		commentRepository.delete(comment);
	}
}