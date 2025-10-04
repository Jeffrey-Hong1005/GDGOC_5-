// package com.gdg.spring_study.repository.PostRepository.java

package com.gdg.spring_study.repository;

import com.gdg.spring_study.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}