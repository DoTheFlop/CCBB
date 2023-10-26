package com.D104.ccbb.event.dto;

import java.time.LocalDateTime;

import com.D104.ccbb.event.domain.Event;

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

	public static EventDto fromEntity(Event event) {
		return EventDto.builder()
			.eventId(event.getEventId())
			.title(event.getTitle())
			.content(event.getContent())
			.createDate(event.getCreateDate())
			.startDate(event.getStartDate())
			.endDate(event.getEndDate())
			.build();
	}

}
