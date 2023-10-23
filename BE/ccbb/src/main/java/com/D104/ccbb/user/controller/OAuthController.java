package com.D104.ccbb.user.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.D104.ccbb.user.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/oauth2")
@RestController
@RequiredArgsConstructor
public class OAuthController {

	private final UserService userService;

	// private final TokenProvider jwtTokenProvider;
	@Value("${spring.security.oauth2.client.registration.kakao.client-id}")
	private String REST_API_KEY;

	@Value("${spring.security.oauth2.client.registration.kakao.redirect-uri}")
	private String REDIRECT_URI;

	@Value("${spring.security.oauth2.client.provider.kakao.token-uri}")
	private String TOKEN_URI;

	@GetMapping("/kakao")
	public void kakaoLogin(HttpServletResponse response) throws IOException {
		try {
			// 카카오 OAuth2 흐름 시작
			String url = String.format(
				"https://kauth.kakao.com/oauth/authorize?response_type=code&client_id=%s&redirect_uri=%s", REST_API_KEY,
				REDIRECT_URI);
			response.sendRedirect(url);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}

	@GetMapping("/kakao/callback")
	public void kakaoLoginCallback(String code, HttpServletResponse response,
		String access_token) {
		// 사용자 정보 가져오기
		log.info("code: {}", code);
		log.info(access_token);

		try {
			// 카카오 OAuth2 흐름 시작
			String url = TOKEN_URI + "?grant_type=authorization_code&" + "client_id=" + REST_API_KEY
				+ "&redirect_uri=" + REDIRECT_URI + "&code=" + code;
			response.sendRedirect(url);

		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}

	@GetMapping("/kakao/me")
	public ResponseEntity<String> kakaoLoginUserToken(@RequestParam("access_token") String accessToken,
		@RequestParam("refresh_token") String refreshToken) {
		log.info("accessToken : {}", accessToken);
		log.info("refreshToken: {}", refreshToken);

		userService.getUserInfoFromKakao(accessToken);
		return new ResponseEntity<>("", HttpStatus.OK);
	}
}