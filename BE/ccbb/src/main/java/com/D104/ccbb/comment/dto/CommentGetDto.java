package com.D104.ccbb.comment.dto;

import java.time.LocalDateTime;

import com.D104.ccbb.comment.domain.Comment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentGetDto {

	private Integer commentId;
	private String content;
	private LocalDateTime createDate;
	private String nickname;
	private Integer postId;

	public static CommentGetDto fromEntity(Comment comment) {
		return CommentGetDto.builder()
			.commentId(comment.getCommentId())
			.content(comment.getContent())
			.createDate(comment.getCreateDate())
			.nickname(comment.getUserId().getNickname())
			.postId(comment.getPostId().getPostId())
			.build();
	}

}
