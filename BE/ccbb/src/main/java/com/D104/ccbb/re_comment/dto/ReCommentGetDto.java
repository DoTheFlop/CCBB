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
public class ReCommentGetDto {
	private Integer reCommentId;
	private String content;
	private LocalDateTime createDate;
	private Integer commentId;
	private String tier;
	private Byte position;
	private String nickname;
	public static ReCommentGetDto fromEntity(ReComment reComment) {
		return ReCommentGetDto.builder()
			.reCommentId(reComment.getReCommentId())
			.content(reComment.getContent())
			.createDate(reComment.getCreateDate())
			.tier(reComment.getUserId().getLol())
			.position(reComment.getUserId().getMainPosition())
			.nickname(reComment.getUserId().getNickname())
			.commentId(reComment.getCommentId().getCommentId())
			.build();
	}
}
