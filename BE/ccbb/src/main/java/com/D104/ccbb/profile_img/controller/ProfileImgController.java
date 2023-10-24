package com.D104.ccbb.profile_img.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.D104.ccbb.profile_img.domain.ProfileImg;

@RestController
public class ProfileImgController {

	public ResponseEntity<ProfileImg> createProfileImg() {
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
