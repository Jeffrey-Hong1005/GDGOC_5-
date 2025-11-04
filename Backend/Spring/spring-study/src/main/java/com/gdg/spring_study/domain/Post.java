// package com.gdg.spring_study.domain.Post.java

package com.gdg.spring_study.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column(nullable = false)
    private String author;

    private LocalDateTime createdDate;

    // 서비스 계층에서 사용할 생성자
    public Post(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    @PrePersist
    public void prePersist() {
        this.createdDate = LocalDateTime.now(); //업데이트 될때마다 현재시간 작성
    }
   
    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }

}
