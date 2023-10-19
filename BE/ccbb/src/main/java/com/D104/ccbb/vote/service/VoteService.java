package com.D104.ccbb.vote.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.D104.ccbb.vote.domain.Vote;
import com.D104.ccbb.vote.dto.VoteDto;
import com.D104.ccbb.vote.repo.VoteRepo;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class VoteService {

	private final VoteRepo voteRepo;

	@Transactional
	public void setVote(VoteDto voteDto) {
		Vote vote = Vote.builder()
			.voteId(voteDto.getVoteId())
			.vote1(voteDto.getVote1())
			.vote2(voteDto.getVote2())
			.neutrality(voteDto.getNeutrality())
			.argument(voteDto.getArgument())
			.accept1(voteDto.getAccept1())
			.accept2(voteDto.getAccept2())
			.deadline(voteDto.getDeadline())
			.tier(voteDto.getTier())
			.build();

		voteRepo.save(vote);
	}
}
