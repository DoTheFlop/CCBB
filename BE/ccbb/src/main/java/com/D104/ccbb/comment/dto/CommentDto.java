package com.D104.ccbb.comment.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {

	private Integer commentId;
	private String content;
	private LocalDateTime createDate;
	private Integer userId;
	private Integer postId;

}
