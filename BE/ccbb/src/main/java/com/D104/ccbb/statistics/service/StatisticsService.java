package com.D104.ccbb.statistics.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.D104.ccbb.ballot_box.domain.BallotBox;
import com.D104.ccbb.ballot_box.repo.BallotBoxRepo;
import com.D104.ccbb.vote.domain.Vote;
import com.D104.ccbb.vote.repo.VoteRepo;
import com.D104.ccbb.vote.service.VoteService;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class StatisticsService {

	private final VoteRepo voteRepo;
	private final BallotBoxRepo ballotBoxRepo;

	public List<Vote> getVoteList(LocalDateTime deadLine){
		return voteRepo.findByDeadlineLessThan(deadLine);
	}

}
