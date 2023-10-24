package com.D104.ccbb.post.dto;

import java.time.LocalDateTime;

import com.D104.ccbb.post.domain.Post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostLoadDto {
	private Integer postId;
	private String title;
	private String content;
	private LocalDateTime createDate;
	private Integer type;
	private String userEmail;

	public static PostLoadDto fromEntity(Post post) {
		return PostLoadDto.builder()
			.postId(post.getPostId())
			.title(post.getTitle())
			.content(post.getContent())
			.createDate(post.getCreateDate())
			.type(post.getType())
			.userEmail(post.getUserId().getEmail())
			.build();
	}
}
