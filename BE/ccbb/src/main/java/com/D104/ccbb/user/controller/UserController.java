package com.D104.ccbb.user.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.D104.ccbb.jwt.service.JwtTokenService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

	private final JwtTokenService jwtTokenService;

	@GetMapping("/login")
	public ResponseEntity<Integer> login(@RequestHeader String Authorization) {
		return new ResponseEntity<>(jwtTokenService.getUserId(jwtTokenService.extractToken(Authorization)),
			HttpStatus.OK);
	}
}
