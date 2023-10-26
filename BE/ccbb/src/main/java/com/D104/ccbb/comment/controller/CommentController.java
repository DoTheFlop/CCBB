package com.D104.ccbb.comment.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.D104.ccbb.comment.dto.CommentDto;
import com.D104.ccbb.comment.dto.CommentGetDto;
import com.D104.ccbb.comment.service.CommentService;
import com.D104.ccbb.jwt.service.JwtTokenService;
import com.D104.ccbb.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {

	private final CommentService commentService;
	private final JwtTokenService jwtTokenService;
	private final UserRepository userRepository;

	@PostMapping("/add")
	public ResponseEntity<Map<String, Object>> add(@RequestHeader String Authorization,
		@RequestBody CommentDto commentDto) {
		// log.info("add: {}", Authorization);
		commentDto.setUserId(
			userRepository.findByEmail(jwtTokenService.getUserEmail((jwtTokenService.extractToken(Authorization))))
				.get()
				.getUserId());
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			commentService.setComment(commentDto);
			resultMap.put("message", "success");
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			resultMap.put("messege", "fail: " + e.getClass().getSimpleName());
			System.out.println(e);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@GetMapping("/")
	public ResponseEntity<Map<String, Object>> add(@RequestParam int postId) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			List<CommentGetDto> commentList = commentService.getComment(postId)
				.stream()
				.map(m -> CommentGetDto.fromEntity(m))
				.collect(Collectors.toList());
			resultMap.put("commentList", commentList);
			resultMap.put("message", "success");
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			//            logger.error("질문 검색 실패", e);
			resultMap.put("message", "fail: " + e.getClass().getSimpleName());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
}
