package com.D104.ccbb.user.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.D104.ccbb.jwt.service.JwtTokenService;
import com.D104.ccbb.post.dto.PostLoadDto;
import com.D104.ccbb.user.dto.UserDto;
import com.D104.ccbb.user.service.AdminService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {
	private final JwtTokenService jwtTokenService;
	private final AdminService adminService;
	@GetMapping("/user-list")
	public ResponseEntity<Map<String, Object>> add() {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			List<UserDto> userList = adminService.getUser()
				.stream()
				.map(m -> UserDto.fromEntity(m))
				.collect(Collectors.toList());
			resultMap.put("userList", userList);
			resultMap.put("message", "success");
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			resultMap.put("message", "fail: " + e.getClass().getSimpleName());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@PutMapping("/suspend-user/{userId}")
	public ResponseEntity<Map<String, Object>> update(@RequestHeader String Authorization ,@PathVariable Integer userId) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status;
		try {
			adminService.updateSuspend(userId);
			resultMap.put("message", "success");
			resultMap.put("Authorization", Authorization);
			status = HttpStatus.OK;
		} catch (Exception e) {
			resultMap.put("message", "fail: " + e);
			resultMap.put("Authorization", Authorization);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@PutMapping("/unsuspend-user/{userId}")
	public ResponseEntity<Map<String, Object>> update2(@RequestHeader String Authorization, @PathVariable Integer userId) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status;
		try {
			adminService.updateUnSuspend(userId);
			resultMap.put("message", "success");
			resultMap.put("Authorization", Authorization);
			status = HttpStatus.OK;
		} catch (Exception e) {
			resultMap.put("message", "fail: " + e);
			resultMap.put("Authorization", Authorization);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
}
