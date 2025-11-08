// package com.gdg.spring_study.controller.CommentController.java
@RestController
@RequiredArgsConstructor
public class CommentController {

	private final CommentService commentService;

	// 특정 게시글에 댓글 작성
	@PostMapping("/posts/{postId}/comments")
	public CommentResponseDto createComment(@PathVariable Long postId, @RequestBody CommentRequestDto requestDto) {
		return commentService.createComment(postId, requestDto);
	}

	// 특정 게시글의 모든 댓글 조회
	@GetMapping("/posts/{postId}/comments")
	public List<CommentResponseDto> getCommentsByPostId(@PathVariable Long postId) {
		return commentService.getCommentsByPostId(postId);
	}

	// 댓글 수정 (댓글 ID로 직접 접근)
	@PutMapping("/comments/{commentId}")
	public CommentResponseDto updateComment(@PathVariable Long commentId, @RequestBody CommentRequestDto requestDto) {
		return commentService.updateComment(commentId, requestDto);
	}

	// 댓글 삭제 (댓글 ID로 직접 접근)
	@DeleteMapping("/comments/{commentId}")
	public void deleteComment(@PathVariable Long commentId) {
		commentService.deleteComment(commentId);
	}
}
