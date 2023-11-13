package com.D104.ccbb.point_history.dto;

import java.time.LocalDateTime;

import com.D104.ccbb.point_history.domain.PointHistory;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PointHistoryDto {
	private Integer id;

	private Integer userId;

	private Integer value;

	private String detail;

	private LocalDateTime createDate;

	public static PointHistoryDto fromEntity(PointHistory pointHistory) {
		return PointHistoryDto.builder()
			.id(pointHistory.getId())
			.detail(pointHistory.getDetail())
			.userId(pointHistory.getUserId().getUserId())
			.value(pointHistory.getValue())
			.createDate(pointHistory.getCreateDate())
			.build();
	}
}
