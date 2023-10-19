package com.D104.ccbb.vote.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.D104.ccbb.vote.dto.VoteDto;
import com.D104.ccbb.vote.service.VoteService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/vote")
@Slf4j
public class VoteController {

	@Autowired
	private VoteService voteService;

	@PostMapping("/add")
	public ResponseEntity<Map<String, Object>> add(@RequestBody VoteDto voteDto) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			voteService.setVote(voteDto);
			resultMap.put("message", "success");
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			resultMap.put("messege", "fail: " + e.getClass().getSimpleName());
			System.out.println(e);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

}
