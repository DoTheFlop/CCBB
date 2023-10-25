package com.D104.ccbb.profile_img.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

	@GetMapping("/{imgName}")
	public ResponseEntity<byte[]> getProfileImg(@PathVariable String imgName) {
		try {
			byte[] img = profileImgService.getProfileImg(imgName);
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.IMAGE_JPEG);
			return new ResponseEntity<>(img, headers, HttpStatus.OK);
		} catch (Exception e) {
			log.error(e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/add")
	public ResponseEntity<ProfileImg> createProfileImg(@RequestHeader String Authorization,
		@RequestParam MultipartFile file) {
		try {
			ProfileImg profileImg = profileImgService.addImg(Authorization, file);
			return new ResponseEntity<>(profileImg, HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			log.error(e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/modify/{profileimgId}")
	public ResponseEntity<ProfileImg> modifyProfileImg(@RequestHeader String Authorization,
		@RequestParam MultipartFile file, @PathVariable int profileimgId) {
		try {
			ProfileImg profileImg = profileImgService.modifyImg(Authorization, file, profileimgId);
			return new ResponseEntity<>(profileImg, HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			log.error(e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/delete/{profileimgId}")
	public ResponseEntity<String> deleteProfileImg(@RequestHeader String Authorization,
		@PathVariable int profileimgId) {
		try {
			String result = profileImgService.deleteImg(Authorization, profileimgId);
			return new ResponseEntity<>(result, HttpStatus.OK);
		} catch (Exception e) {
			log.error(e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
