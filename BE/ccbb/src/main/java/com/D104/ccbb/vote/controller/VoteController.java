package com.D104.ccbb.vote.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.D104.ccbb.jwt.service.JwtTokenService;
import com.D104.ccbb.user.repository.UserRepository;
import com.D104.ccbb.vote.dto.VoteAddDto;
import com.D104.ccbb.vote.service.VoteService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/vote")
@RequiredArgsConstructor
@Slf4j
public class VoteController {

	private final VoteService voteService;
	private final JwtTokenService jwtTokenService;
	private final UserRepository userRepository;

	@PostMapping("/add")
	public ResponseEntity<Map<String, Object>> add(@RequestHeader String Authorization,
		@RequestBody VoteAddDto voteAddDto) {
		voteAddDto.setUserId1(
			userRepository.findByEmail(jwtTokenService.getUserEmail((jwtTokenService.extractToken(Authorization))))
				.get()
				.getUserId());
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			voteService.setVote(voteAddDto);
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
