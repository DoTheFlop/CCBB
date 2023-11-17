package com.D104.ccbb.user.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.D104.ccbb.user.dto.MailDto;
import com.D104.ccbb.user.service.SendEmailService;
import com.D104.ccbb.user.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/check")
public class SendController {
	private final SendEmailService sendEmailService;
	private final UserService userService;
	@GetMapping("/findPw")
	public ResponseEntity<Map<String, Boolean>> pw_find(@RequestParam String userEmail, @RequestParam String userName) {
		Map<String,Boolean> json = new HashMap<>();
		boolean pwFindCheck = userService.userEmailCheck(userEmail,userName);
		json.put("check", pwFindCheck);
		return new ResponseEntity<>(json, HttpStatus.OK);
	}

	@PostMapping("/findPw/sendEmail")
	public void sendEmail(@RequestParam String userEmail,@RequestParam String userName){
		MailDto dto = sendEmailService.createMailAndChangePassword(userEmail, userName);
		sendEmailService.mailSend(dto);
	}
}
