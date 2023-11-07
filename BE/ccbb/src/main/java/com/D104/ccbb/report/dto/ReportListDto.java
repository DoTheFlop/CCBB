package com.D104.ccbb.report.dto;

import java.time.LocalDateTime;

import com.D104.ccbb.comment.domain.Comment;
import com.D104.ccbb.post.domain.Post;
import com.D104.ccbb.re_comment.domain.ReComment;
import com.D104.ccbb.report.domain.Report;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportListDto {
	private Integer reportId;
	private LocalDateTime createDate;
	private Integer userId;
	private Post postId;
	private Comment commentId;
	private ReComment reCommentId;

	public static ReportListDto fromEntity(Report report) {
		return ReportListDto.builder()
			.createDate(report.getCreateDate())
			.userId(report.getUserId().getUserId())
			.postId(report.getPostId())
			.commentId(report.getCommentId())
			.reCommentId(report.getReCommentId())
			.build();
	}

}
