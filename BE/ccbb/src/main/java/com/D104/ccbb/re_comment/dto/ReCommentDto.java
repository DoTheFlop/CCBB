package com.D104.ccbb.re_comment.dto;

import java.time.LocalDateTime;

import com.D104.ccbb.re_comment.domain.ReComment;

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

	public static ReCommentDto fromEntity(ReComment reComment) {
		return ReCommentDto.builder()
			.reCommentId(reComment.getReCommentId())
			.content(reComment.getContent())
			.createDate(reComment.getCreateDate())
			.userId(reComment.getUserId().getUserId())
			.commentId(reComment.getCommentId().getCommentId())
			.build();
	}
}
