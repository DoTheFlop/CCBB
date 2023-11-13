package com.D104.ccbb.point_history.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.D104.ccbb.point_history.dto.PointHistoryDto;
import com.D104.ccbb.point_history.service.PointHistoryService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/point")
public class PointHistoryController {

	private final PointHistoryService pointHistoryService;

	@GetMapping("/get")
	public ResponseEntity<List<PointHistoryDto>> getPointHistory(@RequestHeader String Authorization) {
		try {
			List<PointHistoryDto> result = pointHistoryService.getPointHistory(Authorization);
			return new ResponseEntity<>(result, HttpStatus.OK);
		} catch (Exception e) {
			log.error(e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
