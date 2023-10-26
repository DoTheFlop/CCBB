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
public class PostDto {
	private Integer postId;
	private String title;
	private String content;
	private LocalDateTime createDate;
	private Integer type;
	private Integer userId;

	public static PostDto fromEntity(Post post) {
		return PostDto.builder()
			.postId(post.getPostId())
			.title(post.getTitle())
			.content(post.getContent())
			.createDate(post.getCreateDate())
			.type(post.getType())
			.userId(post.getUserId().getUserId())
			.build();
	}
}
