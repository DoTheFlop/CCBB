package com.D104.ccbb.comment.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.D104.ccbb.comment.domain.Comment;
import com.D104.ccbb.re_comment.domain.ReComment;

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
	private List<ReComment> reComment;

	public static CommentGetDto fromEntity(Comment comment) {
		return CommentGetDto.builder()
			.commentId(comment.getCommentId())
			.content(comment.getContent())
			.createDate(comment.getCreateDate())
			.nickname(comment.getUserId().getNickname())
			.postId(comment.getPostId().getPostId())
			.reComment(comment.getReComment())
			.build();
	}

}
