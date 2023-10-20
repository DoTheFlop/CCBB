package com.D104.ccbb.event.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventDto {

	private Integer eventId;
	private String title;
	private String content;
	private LocalDateTime createDate;
	private LocalDateTime startDate;
	private LocalDateTime endDate;

}
