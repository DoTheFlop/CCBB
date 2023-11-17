package com.D104.ccbb.profile_img.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.D104.ccbb.profile_img.domain.ProfileImg;
import com.D104.ccbb.profile_img.dto.ProfileImgDto;
import com.D104.ccbb.profile_img.service.ProfileImgService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/profileimg")
@RequiredArgsConstructor
public class ProfileImgController {

	private final ProfileImgService profileImgService;

	@GetMapping("/meta")
	private ResponseEntity<?> getProfileImgMetaData(@RequestHeader String Authorization) {
		try {
			ProfileImgDto profileImgDto = profileImgService.getProfileImgMetaData(Authorization);
			return new ResponseEntity<>(profileImgDto, HttpStatus.OK);
		} catch (Exception e) {
			log.error(e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/{userId}")
	public ResponseEntity<Object> getProfileImg(@PathVariable int userId) {
		try {
			byte[] img = profileImgService.getProfileImg(userId);

			if (img == null) {
				return new ResponseEntity<>("Image not found", HttpStatus.OK);
			}

			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.IMAGE_JPEG);
			return new ResponseEntity<>(img, headers, HttpStatus.OK);
		} catch (Exception e) {
			log.error("", e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/modify")
	public ResponseEntity<ProfileImg> modifyProfileImg(@RequestHeader String Authorization,
		@RequestParam MultipartFile file) {
		try {
			ProfileImg profileImg = profileImgService.modifyImg(Authorization, file);
			return new ResponseEntity<>(profileImg, HttpStatus.OK);
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
