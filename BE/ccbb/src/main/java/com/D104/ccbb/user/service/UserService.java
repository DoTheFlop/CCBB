package com.D104.ccbb.user.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.D104.ccbb.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;

	@Value("${spring.security.oauth2.client.provider.kakao.user-info-uri}")
	private String USER_INFO_URI;

	@Value("${spring.security.oauth2.client.registration.kakao.redirect-uri}")
	private String REDIRECT_URI;

	public String getUserInfoFromKakao(String accessToken) {
		// RestTemplate 생성
		RestTemplate restTemplate = new RestTemplate();

		// 요청 URL
		String url = USER_INFO_URI;

		// 헤더 설정
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer " + accessToken);
		headers.set("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

		// HTTP 엔터티 생성 (헤더와 바디 설정)
		HttpEntity<String> entity = new HttpEntity<>(null, headers);

		// GET 요청 보내기
		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

		// 응답 확인
		if (response.getStatusCode().is2xxSuccessful()) {
			String responseBody = response.getBody();
			System.out.println("Response Body: " + responseBody);
		} else {
			System.err.println("HTTP Request Failed with status code: " + response.getStatusCode());
		}
		return "";
	}
}
