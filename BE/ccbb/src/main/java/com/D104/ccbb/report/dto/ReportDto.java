package com.D104.ccbb.report.dto;

import java.time.LocalDateTime;

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
}
