package com.D104.ccbb.event.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.D104.ccbb.event.domain.Event;
import com.D104.ccbb.event.dto.EventDto;
import com.D104.ccbb.event.repo.EventRepo;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class EventService {

	private final EventRepo eventRepo;

	@Transactional
	public Event setEvent(EventDto eventDto) {
		Event event = Event.builder()
			.eventId(eventDto.getEventId())
			.title(eventDto.getTitle())
			.content(eventDto.getContent())
			.createDate(eventDto.getCreateDate())
			.startDate(eventDto.getStartDate())
			.endDate(eventDto.getEndDate())
			.build();
		Event save = eventRepo.save(event);
		return save;
	}

	@Transactional
	public List<Event> getEventList() {
		return eventRepo.findAll();
	}

}
