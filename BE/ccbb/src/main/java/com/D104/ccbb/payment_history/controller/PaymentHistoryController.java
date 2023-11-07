package com.D104.ccbb.payment_history.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.D104.ccbb.payment_history.dto.PaymentHistoryDto;
import com.D104.ccbb.payment_history.service.PaymentHistoryService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
public class PaymentHistoryController {

	private final PaymentHistoryService paymentHistoryService;

	@GetMapping("/get")
	public ResponseEntity<List<PaymentHistoryDto>> getPaymentList(@RequestHeader String Authorization) {
		try {
			List<PaymentHistoryDto> list = paymentHistoryService.getPaymentList(Authorization);
			return new ResponseEntity<>(list, HttpStatus.OK);
		} catch (Exception e) {
			log.error(e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@PostMapping("/add")
	public ResponseEntity<String> addPayment(@RequestParam int postId, @RequestParam int price,
		@RequestHeader String Authorization) {

		try {
			String paymentUrl = paymentHistoryService.addPayment(postId, price, Authorization);
			log.info("결제 준비 완료");
			return new ResponseEntity<>(paymentUrl, HttpStatus.OK);
		} catch (Exception e) {
			log.error(e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	//price=%d&voteId=%d
	@GetMapping("/success")
	public ResponseEntity<String> successPayment(@RequestParam int postId, @RequestParam int price,
		@RequestParam int userId,
		@RequestParam String pg_token, HttpServletResponse response) {

		try {
			boolean result = paymentHistoryService.approvePayment(userId, postId, price, pg_token);
			response.sendRedirect("https://ccbb.pro/payment-success");
			return new ResponseEntity<>("결제 완료", HttpStatus.OK);
		} catch (Exception e) {
			log.error(e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/return-me")
	public ResponseEntity<?> returnMePayment(@RequestHeader String Authorization, @RequestParam Integer voteId) {
		try {
			String result = paymentHistoryService.returnPayment(Authorization, voteId, true);
			return new ResponseEntity<>(result, HttpStatus.OK);
		} catch (Exception e) {
			log.error(e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/return-other")
	public ResponseEntity<?> returnOtherPayment(@RequestHeader String Authorization, @RequestParam Integer voteId) {
		try {
			String result = paymentHistoryService.returnPayment(Authorization, voteId, false);
			return new ResponseEntity<>(result, HttpStatus.OK);
		} catch (Exception e) {
			log.error(e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
