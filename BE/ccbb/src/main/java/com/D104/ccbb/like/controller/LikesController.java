package com.D104.ccbb.like.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.D104.ccbb.jwt.service.JwtTokenService;
import com.D104.ccbb.like.service.LikesService;
import com.D104.ccbb.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/likes")
@RequiredArgsConstructor
@Slf4j
public class LikesController {

	private final LikesService likesService;
	private final JwtTokenService jwtTokenService;
	private final UserRepository userRepository;

}
