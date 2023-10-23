package com.D104.ccbb.vote.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.D104.ccbb.vote.domain.Vote;
import com.D104.ccbb.vote.dto.VoteDto;
import com.D104.ccbb.vote.repo.VoteRepo;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class VoteService {

	private final VoteRepo voteRepo;

	@Transactional
	public void setVote(VoteDto voteDto) {
		Vote vote = Vote.builder()
			.voteId(voteDto.getVoteId())
			.vote1(voteDto.getVote1())
			.vote2(voteDto.getVote2())
			.argument(voteDto.getArgument())
			.accept1(voteDto.getAccept1())
			.accept2(voteDto.getAccept2())
			.voteStart(voteDto.getVoteStart())
			.deadline(voteDto.getDeadline())
			.tier(voteDto.getTier())
			.promise(voteDto.getPromise())
			.build();

		voteRepo.save(vote);
	}
}
