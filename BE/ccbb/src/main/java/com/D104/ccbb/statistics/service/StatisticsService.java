package com.D104.ccbb.statistics.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.D104.ccbb.ballot_box.domain.BallotBox;
import com.D104.ccbb.ballot_box.repo.BallotBoxRepo;
import com.D104.ccbb.statistics.dto.StatisticsDto;
import com.D104.ccbb.statistics.dto.StatisticsResultDto;
import com.D104.ccbb.user.domain.User;
import com.D104.ccbb.user.repository.UserRepository;
import com.D104.ccbb.vote.domain.Vote;
import com.D104.ccbb.vote.dto.VoteResultDto;
import com.D104.ccbb.vote.repo.VoteRepo;
import com.D104.ccbb.vote.service.VoteService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class StatisticsService {

	private final VoteRepo voteRepo;
	private final BallotBoxRepo ballotBoxRepo;
	private final UserRepository userRepository;
	public List<Vote> getVoteList(LocalDateTime deadLine){
		return voteRepo.findByDeadlineLessThan(deadLine);
	}
	public List<Map<String, Object>> getStatisticsTier(){
		List<Map<String, Object>> a = userRepository.getStatisticsTier();
		return a;
	}

	public List<Map<String, Object>> getStatisticsPosition(){
		List<Map<String, Object>> a = userRepository.getStatisticsPosition();
		return a;
	}
	public List<Map<String, Object>> getCountUserPosition(){
		List<Map<String, Object>> a = userRepository.getUserCountPosition();
		return a;
	}
	public List<Map<String, Object>> getCountUserTier(){
		List<Map<String, Object>> a = userRepository.getUserCountTier();
		return a;
	}


}
