package com.gdg.spring_study.repository;

import com.gdg.spring_study.domain.Comment;
import com.gdg.spring_study.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// package com.gdg.spring_study.repository.CommentRepository.java
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByPost(Post post);
}