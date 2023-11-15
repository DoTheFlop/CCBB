package com.D104.ccbb.ballot_box.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.D104.ccbb.ballot_box.dto.BallotBoxDto;
import com.D104.ccbb.ballot_box.dto.BallotResultDto;
import com.D104.ccbb.ballot_box.repo.BallotBoxRepo;
import com.D104.ccbb.ballot_box.service.BallotBoxService;
import com.D104.ccbb.jwt.service.JwtTokenService;
import com.D104.ccbb.payment_history.service.PaymentHistoryService;
import com.D104.ccbb.user.service.UserService;
import com.D104.ccbb.vote.domain.Vote;
import com.D104.ccbb.vote.repo.VoteRepo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/vote")
@RequiredArgsConstructor
@Slf4j
public class BallotBoxController {

	private final BallotBoxService ballotBoxService;
	private final JwtTokenService jwtTokenService;
	private final UserService userService;

	private final BallotBoxRepo ballotBoxRepo;
	private final VoteRepo voteRepo;
	private final PaymentHistoryService paymentHistoryService;
	@GetMapping("/test")
	public ResponseEntity<?> test(){
		List<Integer> voteIdList = ballotBoxRepo.foundSameVote();
		log.info("list: {}", voteIdList);
		for (Integer voteId : voteIdList) {
			log.info("voteId: {}", voteId);
			Vote vote = voteRepo.findByVoteIdAndDoPromise(voteId, false);
			if (vote == null) {
				continue;
			}
			LocalDateTime deadline = vote.getDeadline();
			if (deadline.toLocalDate().isBefore(LocalDate.now())) {
				log.info("환불 시도");
				vote.setDoPromise(true);
				voteRepo.save(vote);
				String result = paymentHistoryService.returnAllPayment(voteId);
				log.info("result: {}", result);
			}
		}

		return new ResponseEntity<>(voteIdList, HttpStatus.OK) ;
	}

	@PostMapping("/ballet/add")//투표함에 표 추가
	public ResponseEntity<Map<String, Object>> add(@RequestHeader String Authorization,
		@RequestBody BallotBoxDto ballotBoxDto) {
		Map<String, Object> resultMap = new HashMap<>();
		ballotBoxDto.setUserId(
			userService.getUserProfile(jwtTokenService.getUserEmail((jwtTokenService.extractToken(Authorization))))
				.getUserId());
		HttpStatus status = null;
		try {
			ballotBoxService.setBallotBox(ballotBoxDto);
			resultMap.put("message", "success");
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			resultMap.put("messege", "fail: " + e.getClass().getSimpleName());
			System.out.println(e);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@GetMapping("/result")//1번2번 전체 표수 가져오기
	public ResponseEntity<Map<String, Object>> getResult(@RequestParam int voteId) {
		Map<String, Object> resultMap = new HashMap<>();
		log.info(String.valueOf(voteId));
		HttpStatus status = null;
		try {
			BallotResultDto ballotResultDto = ballotBoxService.getBallotResult(voteId);
			resultMap.put("voteResult", ballotResultDto);
			resultMap.put("message", "success");
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			//            logger.error("질문 검색 실패", e);
			resultMap.put("message", "fail: " + e.getClass().getSimpleName());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}


	@GetMapping("/userPick")//투표할때 1번, 2번 전체표랑 유저가 투표한거 불러오는거
	public ResponseEntity<Map<String, Object>> getUserPick(@RequestHeader String Authorization,@RequestParam int voteId) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			BallotResultDto ballotResultDto = ballotBoxService.getUserPick(voteId,userService.getUserProfile(jwtTokenService.getUserEmail((jwtTokenService.extractToken(Authorization))))
				.getUserId());
			resultMap.put("voteResult", ballotResultDto);
			resultMap.put("message", "success");
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			//            logger.error("질문 검색 실패", e);
			resultMap.put("message", "fail: " + e.getClass().getSimpleName());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

}
