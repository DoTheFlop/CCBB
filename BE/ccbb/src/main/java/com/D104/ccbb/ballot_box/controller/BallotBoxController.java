package com.D104.ccbb.ballot_box.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.D104.ccbb.ballot_box.dto.BallotBoxDto;
import com.D104.ccbb.ballot_box.service.BallotBoxService;
import com.D104.ccbb.jwt.service.JwtTokenService;
import com.D104.ccbb.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/vote/ballot")
@RequiredArgsConstructor
public class BallotBoxController {

	private final BallotBoxService ballotBoxService;
	private final JwtTokenService jwtTokenService;
	private final UserRepository userRepository;

	@PostMapping("/add")
	public ResponseEntity<Map<String, Object>> add(@RequestHeader String Authorization,
		@RequestBody BallotBoxDto ballotBoxDto) {
		Map<String, Object> resultMap = new HashMap<>();
		ballotBoxDto.setUserId(
			userRepository.findByEmail(jwtTokenService.getUserEmail((jwtTokenService.extractToken(Authorization))))
				.get()
				.getUserId());
		HttpStatus status = null;
		try {
			ballotBoxService.setBallotBox(ballotBoxDto);
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
