package com.D104.ccbb.post.dto;

import java.time.LocalDateTime;

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
}
