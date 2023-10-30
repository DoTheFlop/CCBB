package com.D104.ccbb.payment_history.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.D104.ccbb.payment_history.service.PaymentHistoryService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
public class PaymentHistoryController {

	private final PaymentHistoryService paymentHistoryService;

	@PostMapping("/add")
	public ResponseEntity<String> addPayment(@RequestParam int voteId, @RequestParam int price,
		@RequestHeader String Authorization) {

		try {
			paymentHistoryService.addPayment(voteId, price, Authorization);
			return new ResponseEntity<>("결제 준비 완료", HttpStatus.OK);
		} catch (Exception e) {
			log.error(e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	//price=%d&voteId=%d
	@GetMapping("/success")
	public ResponseEntity<String> successPayment(@RequestParam int voteId, @RequestParam int price,
		@RequestParam int userId,
		@RequestParam String pg_token, HttpServletResponse response) {

		try {
			boolean result = paymentHistoryService.approvePayment(userId, voteId, price, pg_token);
			response.sendRedirect("https://ccbb.pro/payment-success");
			return new ResponseEntity<>("결제 완료", HttpStatus.OK);
		} catch (Exception e) {
			log.error(e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
