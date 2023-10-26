package com.D104.ccbb.user.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
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
	//
	// @GetMapping("/kakao")
	// public void kakaoLogin(HttpServletResponse response) throws IOException {
	// 	try {
	// 		// 카카오 OAuth2 흐름 시작
	// 		String url = String.format(
	// 			"https://kauth.kakao.com/oauth/authorize?response_type=code&client_id=%s&redirect_uri=%s", REST_API_KEY,
	// 			REDIRECT_URI);
	// 		response.sendRedirect(url);
	// 	} catch (Exception e) {
	// 		log.error(e.getMessage());
	// 	}
	// }
	//
	// @GetMapping("/kakao/callback")
	// public void kakaoLoginCallback(String code, HttpServletResponse response,
	// 	String access_token) {
	// 	// 사용자 정보 가져오기
	// 	log.info("code: {}", code);
	// 	log.info(access_token);
	//
	// 	try {
	// 		// 카카오 OAuth2 흐름 시작
	// 		String url = TOKEN_URI + "?grant_type=authorization_code&" + "client_id=" + REST_API_KEY
	// 			+ "&redirect_uri=" + REDIRECT_URI + "&code=" + code;
	// 		response.sendRedirect(url);
	//
	// 	} catch (Exception e) {
	// 		log.error(e.getMessage());
	// 	}
	// }
	//
	// @GetMapping("/kakao/me")
	// public ResponseEntity<String> kakaoLoginUserToken(@RequestHeader String accessToken,
	// 	@RequestHeader(required = false) String refreshToken) {
	// 	log.info("accessToken : {}", accessToken);
	// 	log.info("refreshToken: {}", refreshToken);
	//
	// 	try {
	// 		String userInfoFromKakao = userService.getUserInfoFromKakao(accessToken);
	// 		return new ResponseEntity<>(userInfoFromKakao, HttpStatus.OK);
	// 	} catch (Exception e) {
	// 		return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	// 	}
	// }

}