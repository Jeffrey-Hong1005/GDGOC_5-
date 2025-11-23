package com.gdg.spring_study.domain;// package com.gdg.spring_study.domain.Comment.java

import com.gdg.spring_study.domain.Post;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Comment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String content;

	@Column(nullable = false)
	private String author;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "post_id")
	private Post post;

	public Comment(String content, String author, Post post) {
		this.content = content;
		this.author = author;
		this.post = post;
	}

    public void update(String content, String author) {
        this.content = content;
        this.author = author;
    }
}
