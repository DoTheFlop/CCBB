package com.D104.ccbb.user.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.D104.ccbb.jwt.service.JwtTokenService;
import com.D104.ccbb.user.dto.UserDto;
import com.D104.ccbb.user.dto.UserEmailPasDto;
import com.D104.ccbb.user.dto.UserLoginDto;
import com.D104.ccbb.user.service.SendEmailService;
import com.D104.ccbb.user.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

	private final JwtTokenService jwtTokenService;
	private final UserService userService;
	private final SendEmailService sendEmailService;

	@GetMapping("/login")
	public ResponseEntity<String> login(@RequestHeader String Authorization) {
		log.info("login 호출: {}", Authorization);
		return new ResponseEntity<>(jwtTokenService.getUserEmail(jwtTokenService.extractToken(Authorization)),
			HttpStatus.OK);
	}

	@GetMapping("/find-user")
	public ResponseEntity<Boolean> findUser(@RequestParam String email) {
		log.info("유저 검색 : {}", email);
		try {
			return new ResponseEntity<>(userService.findUser(email), HttpStatus.OK);
		} catch (UsernameNotFoundException e) {
			log.error(e.getMessage());
			return new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/sign-up")
	public ResponseEntity<String> signUp() {
		return new ResponseEntity<>("회원가입", HttpStatus.OK);
	}

	@PostMapping("/esign-up")
	public ResponseEntity<String> esignup(@RequestBody UserLoginDto userLoginDto) {
		userService.eSignup(userLoginDto);
		return new ResponseEntity<>("회원가입 완료", HttpStatus.CREATED);
	}

	@PostMapping("/elogin")
	public ResponseEntity<String> login(@RequestBody UserEmailPasDto userEmailPasDto) {
		String token = userService.elogin(userEmailPasDto);
		return new ResponseEntity<>(token, HttpStatus.OK);
	}

	@PutMapping("/modify")
	public ResponseEntity<Map<String, Object>> update(@RequestHeader String Authorization,
		@RequestBody UserDto userDto) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status;

		try {

			userService.updateUser(jwtTokenService.getUserEmail(jwtTokenService.extractToken(Authorization)), userDto);
			resultMap.put("message", "success");
			resultMap.put("Authorization", Authorization);
			status = HttpStatus.OK;
		} catch (Exception e) {
			log.error("", e);
			resultMap.put("message", "fail: " + e);
			resultMap.put("Authorization", Authorization);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@GetMapping("/profile")
	public ResponseEntity<Map<String, Object>> add(@RequestHeader String Authorization) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			UserDto userDto = userService.getUserProfile(
				jwtTokenService.getUserEmail(jwtTokenService.extractToken(Authorization)));
			resultMap.put("user", userDto);
			resultMap.put("message", "success");
			resultMap.put("Authorization", Authorization);
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			log.error("", e);
			resultMap.put("message", "fail: " + e.getClass().getSimpleName());
			resultMap.put("Authorization", Authorization);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@DeleteMapping("/quit")
	public ResponseEntity<Map<String, Object>> deleteMyProfile(@RequestHeader String Authorization) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status;
		try {
			userService.deleteUser(jwtTokenService.getUserEmail(jwtTokenService.extractToken(Authorization)));
			resultMap.put("message", "success");
			resultMap.put("Authorization", Authorization);
			status = HttpStatus.OK;
		} catch (Exception e) {
			log.error("", e);
			resultMap.put("message", "fail: " + e);
			resultMap.put("Authorization", Authorization);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@GetMapping("/checkPw")
	public ResponseEntity<Map<String, Object>> checkPw(@RequestHeader String Authorization,
		@RequestParam String userPw) {
		Map<String, Object> json = new HashMap<>();
		HttpStatus status;
		try {
			boolean pwCheck = userService.userPwCheck(
				jwtTokenService.getUserEmail(jwtTokenService.extractToken(Authorization)), userPw);
			json.put("check", pwCheck);
			json.put("message", "success");
			json.put("Authorization", Authorization);
			status = HttpStatus.OK;
		} catch (Exception e) {
			log.error("", e);
			json.put("message", "fail: " + e);
			json.put("Authorization", Authorization);
			log.error("비밀번호 확인 에러", e);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<>(json, status);
	}

	@PostMapping("/lol/tier")
	public ResponseEntity<Boolean> updateLolTier(@RequestHeader String Authorization,
		@RequestParam String lolName) {
		try {
			log.info(lolName);
			boolean result = userService.updateLolTier(Authorization, lolName);
			return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			log.error(e.getMessage());
			return new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
