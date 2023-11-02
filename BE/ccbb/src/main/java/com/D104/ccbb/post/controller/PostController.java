package com.D104.ccbb.post.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.D104.ccbb.file.service.FileService;
import com.D104.ccbb.jwt.service.JwtTokenService;
import com.D104.ccbb.like.dto.LikesDto;
import com.D104.ccbb.like.repo.LikesRepo;
import com.D104.ccbb.like.service.LikesService;
import com.D104.ccbb.post.domain.Post;
import com.D104.ccbb.post.dto.PostDto;
import com.D104.ccbb.post.dto.PostLoadDto;
import com.D104.ccbb.post.repo.PostRepo;
import com.D104.ccbb.post.service.PostService;
import com.D104.ccbb.user.repository.UserRepository;
import com.D104.ccbb.user.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
@Slf4j
public class PostController {

	private final PostService postService;
	private final JwtTokenService jwtTokenService;
	private final UserRepository userRepository;
	private final LikesService likesService;
	private final LikesRepo likesRepo;
	private final PostRepo postRepo;
	private final FileService fileService;
	private final UserService userService;
	// private final FileService fileService;

	// public ResponseEntity<Map<String, Object>> add(@RequestHeader String Authorization, @RequestBody PostDto postDto, @RequestParam List<MultipartFile> files) {

	@PostMapping("/add")
	public ResponseEntity<Map<String, Object>> add(@RequestHeader String Authorization,
		@RequestPart(value = "files", required = false) List<MultipartFile> files,
		@RequestPart(value = "post") PostDto postDto) {
		log.info(String.valueOf(postDto));
		if (files != null) {
			for (MultipartFile a : files) {
				log.info(a.getOriginalFilename());
			}
		}

		postDto.setUserId(
			userService.getUserProfile(jwtTokenService.getUserEmail((jwtTokenService.extractToken(Authorization))))
				.getUserId());
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			Post post = postService.setPost(postDto);
			if (files != null)
				fileService.saveFile(files, "post", post.getPostId());
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
			// List<PostLoadDto> voteList = postService.getVote()
			// 	.stream()
			// 	.map(m -> PostLoadDto.fromEntity(m))
			// 	.collect(Collectors.toList());

			resultMap.put("voteList", postService.getVote());
			resultMap.put("message", "success");
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			resultMap.put("message", "fail: " + e.getMessage());
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
			likesService.setPostLike(likesDto);
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
	public ResponseEntity<Map<String, Object>> deletePost(@RequestHeader String Authorization,
		@RequestParam int postId) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		if (postRepo.getReferenceById(postId).getUserId().getUserId() == userService.getUserProfile(jwtTokenService.getUserEmail((jwtTokenService.extractToken(Authorization))))
			.getUserId()){
			try {
				postService.deletePost(postId);
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
		@RequestBody PostDto postDto) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		if (postRepo.getReferenceById(postDto.getPostId()).getUserId().getUserId()
			== userService.getUserProfile(jwtTokenService.getUserEmail((jwtTokenService.extractToken(Authorization))))
			.getUserId()){
			try {
				postService.modifyPost(postDto);
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
