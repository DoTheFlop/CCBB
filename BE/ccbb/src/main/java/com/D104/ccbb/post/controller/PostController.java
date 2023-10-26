package com.D104.ccbb.post.controller;

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
import org.springframework.web.bind.annotation.RestController;

import com.D104.ccbb.jwt.service.JwtTokenService;
import com.D104.ccbb.post.dto.PostDto;
import com.D104.ccbb.post.dto.PostLoadDto;
import com.D104.ccbb.post.service.PostService;
import com.D104.ccbb.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {

	private final PostService postService;
	private final JwtTokenService jwtTokenService;
	private final UserRepository userRepository;

	@PostMapping("/add")
	public ResponseEntity<Map<String, Object>> add(@RequestHeader String Authorization, @RequestBody PostDto postDto) {
		postDto.setUserId(
			userRepository.findByEmail(jwtTokenService.getUserEmail((jwtTokenService.extractToken(Authorization))))
				.get()
				.getUserId());
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			postService.setPost(postDto);
			resultMap.put("message", "success");
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			resultMap.put("messege", "fail: " + e.getClass().getSimpleName());
			System.out.println(e);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@GetMapping("/free/list")
	public ResponseEntity<Map<String, Object>> add1() {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			List<PostLoadDto> freeList = postService.getFree()
				.stream()
				.map(m -> PostLoadDto.fromEntity(m))
				.collect(Collectors.toList());
			resultMap.put("freeList", freeList);
			resultMap.put("message", "success");
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			resultMap.put("message", "fail: " + e.getClass().getSimpleName());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@GetMapping("/vote/list")
	public ResponseEntity<Map<String, Object>> add2() {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			List<PostLoadDto> voteList = postService.getVote()
				.stream()
				.map(m -> PostLoadDto.fromEntity(m))
				.collect(Collectors.toList());
			resultMap.put("voteList", voteList);
			resultMap.put("message", "success");
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			resultMap.put("message", "fail: " + e.getClass().getSimpleName());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
}
