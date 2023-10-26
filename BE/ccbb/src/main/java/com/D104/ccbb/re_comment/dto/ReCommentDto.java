package com.D104.ccbb.re_comment.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReCommentDto {
	private Integer reCommentId;
	private String content;
	private LocalDateTime createDate;
	private Integer userId;
	private Integer commentId;
}
