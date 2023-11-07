package com.D104.ccbb.event.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.D104.ccbb.event.domain.Event;
import com.D104.ccbb.event.dto.EventDto;
import com.D104.ccbb.event.service.EventService;
import com.D104.ccbb.file.service.FileService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/event")
@RequiredArgsConstructor
public class EventController {

	private final EventService eventService;
	private final FileService fileService;

	@PostMapping("/add")
	public ResponseEntity<Map<String, Object>> add(
		@RequestPart(value = "files", required = false) List<MultipartFile> files,
		@RequestPart(value = "event") EventDto eventDto) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			Event event = eventService.setEvent(eventDto);
			if (files != null)
				fileService.saveFile(files, "event", event.getEventId());
			resultMap.put("message", "success");
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			resultMap.put("messege", "fail: " + e.getClass().getSimpleName());
			System.out.println(e);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@GetMapping("/list")
	public ResponseEntity<Map<String, Object>> add() {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			List<EventDto> eventList = eventService.getEventList()
				.stream()
				.map(m -> EventDto.fromEntity(m))
				.collect(Collectors.toList());
			resultMap.put("eventList", eventList);
			resultMap.put("message", "success");
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			//            logger.error("질문 검색 실패", e);
			resultMap.put("message", "fail: " + e.getClass().getSimpleName());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

}
