package com.D104.ccbb.user.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/oauth2")
@RestController
@RequiredArgsConstructor
public class OAuthController {

	// private final TokenProvider jwtTokenProvider;

	@GetMapping("/kakao")
	public String kakaoLogin() {
		// 카카오 OAuth2 흐름 시작
		return "redirect:/oauth2/authorization/kakao";
	}

	@GetMapping("/kakao/callback")
	public ResponseEntity<String> kakaoLoginCallback(@AuthenticationPrincipal OAuth2User principal) {
		// 사용자 정보 가져오기
		// principal에서 사용자 정보 추출

		// JWT 토큰 생성
		String token = ""; //jwtTokenProvider.createAccessToken(1L, jwtTokenProvider.getSecretKey());

		// JWT 토큰을 클라이언트로 반환
		return ResponseEntity.ok(token);
	}
}