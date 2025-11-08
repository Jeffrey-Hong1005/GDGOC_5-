// package com.gdg.spring_study.domain.Comment.java

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
}
