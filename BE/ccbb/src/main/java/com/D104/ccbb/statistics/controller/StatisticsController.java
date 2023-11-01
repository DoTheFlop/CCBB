package com.D104.ccbb.statistics.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.D104.ccbb.ballot_box.dto.BallotBoxDto;
import com.D104.ccbb.ballot_box.dto.BallotResultDto;
import com.D104.ccbb.ballot_box.service.BallotBoxService;
import com.D104.ccbb.post.dto.PostLoadDto;
import com.D104.ccbb.statistics.service.StatisticsService;
import com.D104.ccbb.user.repository.UserRepository;
import com.D104.ccbb.user.service.UserService;
import com.D104.ccbb.vote.dto.VoteResultDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/statistics")
@RequiredArgsConstructor
@Slf4j
public class StatisticsController {

	private final StatisticsService statisticsService;
	private final UserService userService;
	private final BallotBoxService ballotBoxService;
	// @PutMapping("/update")
	@GetMapping("/update")
	public ResponseEntity<Map<String, Object>> add2() {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			List<VoteResultDto> voteResultList = statisticsService.getVoteList(LocalDateTime.now())
				.stream()
				.map(m -> VoteResultDto.fromEntity(m))
				.collect(Collectors.toList());
			for(VoteResultDto a : voteResultList){
				BallotResultDto ballotResultDto = ballotBoxService.getBallotResult(a.getVoteId());
				if(ballotResultDto.getPick1()>ballotResultDto.getPick2())
					a.setWin(1);
				else if(ballotResultDto.getPick1()==ballotResultDto.getPick2())
					a.setWin(0);
				else a.setWin(2);
			}
			for(VoteResultDto a : voteResultList){
				List<BallotBoxDto> ballotBoxDto = ballotBoxService.getBallotList(a.getVoteId())
					.stream()
					.map(m -> BallotBoxDto.fromEntity(m))
					.collect(Collectors.toList());
				if(a.getWin()==0){
					for(BallotBoxDto b : ballotBoxDto){
						userService.updateVote(b.getUserId(),1);
					}
				}
				else if(a.getWin()==1){
					for(BallotBoxDto b : ballotBoxDto){
						if(b.getPick()==1)
						userService.updateVote(b.getUserId(),1);
						else userService.updateVote(b.getUserId(),0);
					}
				}
				else{
					for(BallotBoxDto b : ballotBoxDto){
						if(b.getPick()==2)
							userService.updateVote(b.getUserId(),1);
						else userService.updateVote(b.getUserId(),0);
					}
				}
			}
			resultMap.put("message", "success");
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			resultMap.put("message", "fail: " + e.getClass().getSimpleName());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@GetMapping("/vote/tier")
	public ResponseEntity<Map<String, Object>> getTier() {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			resultMap.put("statistics", statisticsService.getStatisticsTier());
			resultMap.put("message", "success");
			status = HttpStatus.ACCEPTED;
		}catch (Exception e) {
			resultMap.put("message", "fail: " + e.getClass().getSimpleName());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	@GetMapping("/vote/position")
	public ResponseEntity<Map<String, Object>> getPosition() {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			resultMap.put("statistics", statisticsService.getStatisticsPosition());
			resultMap.put("message", "success");
			status = HttpStatus.ACCEPTED;
		}catch (Exception e) {
			resultMap.put("message", "fail: " + e.getClass().getSimpleName());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@GetMapping("/count/tier")
	public ResponseEntity<Map<String, Object>> getCountTier() {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			resultMap.put("statistics", statisticsService.getCountUserTier());
			resultMap.put("message", "success");
			status = HttpStatus.ACCEPTED;
		}catch (Exception e) {
			resultMap.put("message", "fail: " + e.getClass().getSimpleName());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@GetMapping("/count/position")
	public ResponseEntity<Map<String, Object>> getCountPosition() {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			resultMap.put("statistics", statisticsService.getCountUserPosition());
			resultMap.put("message", "success");
			status = HttpStatus.ACCEPTED;
		}catch (Exception e) {
			resultMap.put("message", "fail: " + e.getClass().getSimpleName());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
}
