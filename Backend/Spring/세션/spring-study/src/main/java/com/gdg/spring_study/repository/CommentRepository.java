// package com.gdg.spring_study.repository.CommentRepository.java
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByPost(Post post);
}