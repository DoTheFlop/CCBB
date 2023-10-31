package com.D104.ccbb.statistics.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.D104.ccbb.post.dto.PostLoadDto;
import com.D104.ccbb.statistics.service.StatisticsService;
import com.D104.ccbb.user.repository.UserRepository;
import com.D104.ccbb.vote.dto.VoteResultDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/statistics")
@RequiredArgsConstructor
@Slf4j
public class StatisticsController {

	private final StatisticsService statisticsService;
	private final UserRepository userRepository;

	// @PutMapping("/update")
	@GetMapping("/update")
	public ResponseEntity<Map<String, Object>> add2() {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			List<VoteResultDto> voteResultList = statisticsService.getVoteList(LocalDateTime.now())
				.stream()
				.map(m -> VoteResultDto.fromEntity(m))
				.collect(Collectors.toList());
			resultMap.put("voteResultList", voteResultList);
			resultMap.put("message", "success");
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			resultMap.put("message", "fail: " + e.getClass().getSimpleName());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

}
