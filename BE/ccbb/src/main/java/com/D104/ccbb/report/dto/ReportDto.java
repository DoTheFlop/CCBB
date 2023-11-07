package com.D104.ccbb.report.dto;

import java.time.LocalDateTime;

import com.D104.ccbb.report.domain.Report;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportDto {
	private Integer reportId;
	private LocalDateTime createDate;
	private Integer userId;
	private Integer postId;
	private Integer commentId;
	private Integer reCommentId;

	public static ReportDto fromEntity(Report report) {
		if (report.getPostId() != null) {
			return ReportDto.builder()
				.createDate(report.getCreateDate())
				.postId(report.getPostId().getPostId())
				.build();
		} else if (report.getCommentId() != null) {
			return ReportDto.builder()
				.createDate(report.getCreateDate())
				.commentId(report.getCommentId().getCommentId())
				.build();
		} else if (report.getReCommentId() != null) {
			return ReportDto.builder()
				.createDate(report.getCreateDate())
				.reCommentId(report.getReCommentId().getReCommentId())
				.build();
		} else {
			return ReportDto.builder()
				.createDate(report.getCreateDate())
				.build();
		}
	}

}
