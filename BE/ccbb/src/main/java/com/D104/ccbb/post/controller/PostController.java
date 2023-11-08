package com.D104.ccbb.post.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
import com.D104.ccbb.vote.dto.VoteAcceptDto;
import com.D104.ccbb.vote.dto.VoteListDto;
import com.D104.ccbb.vote.service.VoteService;

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
	private final VoteService voteService;
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
			resultMap.put("postId", post.getPostId());
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

	@GetMapping("/vote/detail")
	public ResponseEntity<Map<String, Object>> detail(@RequestParam int postId) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			resultMap.put("voteList", postService.getDetail(postId));
			resultMap.put("message", "success");
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			resultMap.put("message", "fail: " + e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@GetMapping("/vote/list")
	public ResponseEntity<Map<String, Object>> list(@RequestParam int page) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			resultMap.put("voteList", postService.getPageList(page));
			resultMap.put("message", "success");
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			resultMap.put("message", "fail: " + e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@GetMapping("/vote/popularList")
	public ResponseEntity<Map<String, Object>> popularList(@RequestParam int page) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			resultMap.put("voteList", postService.getPopularityPage(page));
			resultMap.put("message", "success");
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			resultMap.put("message", "fail: " + e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@GetMapping("/vote/participationList")
	public ResponseEntity<Map<String, Object>> participationList(@RequestHeader String Authorization) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		List<VoteListDto> partList = voteService.getParticipationList(
			userService.getUserProfile(jwtTokenService.getUserEmail((jwtTokenService.extractToken(Authorization))))
				.getUserId());
		try {
			resultMap.put("participationList", partList);
			resultMap.put("message", "success");
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			resultMap.put("message", "fail: " + e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@GetMapping("/vote/acceptList")
	public ResponseEntity<Map<String, Object>> acceptList(@RequestHeader String Authorization) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		List<VoteAcceptDto> acceptList = voteService.getNotAccept(
			userService.getUserProfile(jwtTokenService.getUserEmail((jwtTokenService.extractToken(Authorization))))
				.getUserId());
		try {
			resultMap.put("participationList", acceptList);
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
		if (likesRepo.getReferenceById(likesId).getUserId().getUserId() == userService.getUserProfile(
				jwtTokenService.getUserEmail((jwtTokenService.extractToken(Authorization))))
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
	public ResponseEntity<Map<String, Object>> deletePost(@RequestHeader String Authorization,
		@RequestParam int postId) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		if (postRepo.getReferenceById(postId).getUserId().getUserId() == userService.getUserProfile(
				jwtTokenService.getUserEmail((jwtTokenService.extractToken(Authorization))))
			.getUserId()) {
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
			.getUserId()) {
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

	@DeleteMapping("/reject/{postId}")
	public ResponseEntity<String> reject(@RequestHeader String Authorization, @PathVariable int postId) {
		try {
			postService.rejectPost(postId, Authorization);
			return new ResponseEntity<>("success", HttpStatus.OK);
		} catch (Exception e) {
			log.error(e.getMessage());
			return new ResponseEntity<>("fail", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
