package com.D104.ccbb.profile_img.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.D104.ccbb.profile_img.domain.ProfileImg;
import com.D104.ccbb.profile_img.service.ProfileImgService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/profileimg")
@RequiredArgsConstructor
public class ProfileImgController {

	private final ProfileImgService profileImgService;

	@PostMapping("/add")
	public ResponseEntity<ProfileImg> createProfileImg(@RequestHeader String Authorization,
		@RequestParam MultipartFile file) {
		try {
			profileImgService.addImg(Authorization, file);
		} catch (Exception e) {
			log.error(e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
