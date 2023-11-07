package com.D104.ccbb.re_comment.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.D104.ccbb.jwt.service.JwtTokenService;
import com.D104.ccbb.like.dto.LikesDto;
import com.D104.ccbb.like.repo.LikesRepo;
import com.D104.ccbb.like.service.LikesService;
import com.D104.ccbb.re_comment.dto.ReCommentDto;
import com.D104.ccbb.re_comment.repo.ReCommentRepo;
import com.D104.ccbb.re_comment.service.ReCommentService;
import com.D104.ccbb.user.repository.UserRepository;
import com.D104.ccbb.user.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/recomment")
@RequiredArgsConstructor
public class ReCommentController {

	private final ReCommentService reCommentService;
	private final JwtTokenService jwtTokenService;
	private final UserRepository userRepository;
	private final LikesService likesService;
	private final LikesRepo likesRepo;
	private final ReCommentRepo reCommentRepo;
	private final UserService userService;
	@PostMapping("/add")
	public ResponseEntity<Map<String, Object>> add(@RequestHeader String Authorization,
		@RequestBody ReCommentDto reCommentDto) {
		// log.info("add: {}", Authorization);
		reCommentDto.setUserId(
			userService.getUserProfile(jwtTokenService.getUserEmail((jwtTokenService.extractToken(Authorization))))
				.getUserId());
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			reCommentService.setReCommentRepo(reCommentDto);
			resultMap.put("message", "success");
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			resultMap.put("messege", "fail: " + e.getClass().getSimpleName());
			System.out.println(e);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@PostMapping("/likes/add")
	public ResponseEntity<Map<String, Object>> add(@RequestHeader String Authorization,
		@RequestBody LikesDto likesDto) {
		likesDto.setUserId(
			userService.getUserProfile(jwtTokenService.getUserEmail((jwtTokenService.extractToken(Authorization))))
				.getUserId());
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			likesService.setReCommentLike(likesDto);
			resultMap.put("message", "success");
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			resultMap.put("messege", "fail: " + e.getClass().getSimpleName());
			System.out.println(e);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@DeleteMapping("/likes/delete")
	public ResponseEntity<Map<String, Object>> delete(@RequestHeader String Authorization,
		@RequestParam int likesId) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		if (likesRepo.getReferenceById(likesId).getUserId().getUserId() == userService.getUserProfile(jwtTokenService.getUserEmail((jwtTokenService.extractToken(Authorization))))
			.getUserId()){
			try {
				likesService.deleteLikes(likesId);
				resultMap.put("message", "success");
				status = HttpStatus.ACCEPTED;
			} catch (Exception e) {
				resultMap.put("messege", "fail: " + e.getClass().getSimpleName());
				System.out.println(e);
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@DeleteMapping("/delete")
	public ResponseEntity<Map<String, Object>> deleteReComment(@RequestHeader String Authorization,
		@RequestParam int reCommentId) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		if (reCommentRepo.getReferenceById(reCommentId).getUserId().getUserId() == userService.getUserProfile(jwtTokenService.getUserEmail((jwtTokenService.extractToken(Authorization))))
			.getUserId()){
			try {
				reCommentService.deleteReComment(reCommentId);
				resultMap.put("message", "success");
				status = HttpStatus.ACCEPTED;
			} catch (Exception e) {
				resultMap.put("messege", "fail: " + e.getClass().getSimpleName());
				System.out.println(e);
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@PutMapping("/modify")
	public ResponseEntity<Map<String, Object>> modify(@RequestHeader String Authorization,
		@RequestBody ReCommentDto reCommentDto) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		if (reCommentRepo.getReferenceById(reCommentDto.getReCommentId()).getUserId().getUserId()
			== userService.getUserProfile(jwtTokenService.getUserEmail((jwtTokenService.extractToken(Authorization))))
			.getUserId()){
			try {
				reCommentService.modifyReComment(reCommentDto);
				resultMap.put("message", "success");
				status = HttpStatus.ACCEPTED;
			} catch (Exception e) {
				resultMap.put("message", "fail: " + e.getClass().getSimpleName());
				System.out.println(e);
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
}