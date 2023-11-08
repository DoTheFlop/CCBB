package com.D104.ccbb.comment.controller;

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
import org.springframework.web.bind.annotation.RestController;

import com.D104.ccbb.comment.dto.CommentDto;
import com.D104.ccbb.comment.dto.CommentGetDto;
import com.D104.ccbb.comment.dto.CommentRequestDto;
import com.D104.ccbb.comment.repo.CommentRepo;
import com.D104.ccbb.comment.service.CommentService;
import com.D104.ccbb.jwt.service.JwtTokenService;
import com.D104.ccbb.like.dto.LikesDto;
import com.D104.ccbb.like.repo.LikesRepo;
import com.D104.ccbb.like.service.LikesService;
import com.D104.ccbb.user.domain.User;
import com.D104.ccbb.user.repository.UserRepository;
import com.D104.ccbb.user.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {

	private final CommentService commentService;
	private final JwtTokenService jwtTokenService;
	private final UserRepository userRepository;
	private final LikesService likesService;
	private final LikesRepo likesRepo;
	private final CommentRepo commentRepo;
	private final UserService userService;

	@PostMapping("/add")
	public ResponseEntity<Map<String, Object>> add(@RequestHeader String Authorization,
		@RequestBody CommentRequestDto commentRequestDto) {
		User writer = userRepository.findByEmail(
			jwtTokenService.getUserEmail((jwtTokenService.extractToken(Authorization)))).get();
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			commentService.setComment(commentRequestDto, writer);
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

	@PostMapping("/likes/add")
	public ResponseEntity<Map<String, Object>> add(@RequestHeader String Authorization,
		@RequestBody LikesDto likesDto) {
		likesDto.setUserId(
			userService.getUserProfile(jwtTokenService.getUserEmail((jwtTokenService.extractToken(Authorization))))
				.getUserId());
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			likesService.setCommentLike(likesDto);
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
	public ResponseEntity<Map<String, Object>> deleteLikes(@RequestHeader String Authorization,
		@RequestParam int likesId) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		if (likesRepo.getReferenceById(likesId).getUserId().getUserId() == userRepository.findByEmail(
				jwtTokenService.getUserEmail((jwtTokenService.extractToken(Authorization))))
			.get()
			.getUserId()) {
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
	public ResponseEntity<Map<String, Object>> deleteComment(@RequestHeader String Authorization,
		@RequestParam int commentId) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		if (commentRepo.findById(commentId).get().getUserId().getUserId() == userService.getUserProfile(
				jwtTokenService.getUserEmail((jwtTokenService.extractToken(Authorization))))
			.getUserId()) {
			try {
				commentService.deleteComment(commentId);
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
		@RequestBody CommentDto commentDto) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		if (commentRepo.findById(commentDto.getCommentId()).get().getUserId().getUserId()
			== userService.getUserProfile(jwtTokenService.getUserEmail((jwtTokenService.extractToken(Authorization))))
			.getUserId()) {
			try {
				commentService.modifyComment(commentDto);
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
