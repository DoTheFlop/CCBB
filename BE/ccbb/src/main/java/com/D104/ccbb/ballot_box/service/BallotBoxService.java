package com.D104.ccbb.ballot_box.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.D104.ccbb.ballot_box.domain.BallotBox;
import com.D104.ccbb.ballot_box.dto.BallotBoxDto;
import com.D104.ccbb.ballot_box.dto.BallotResultDto;
import com.D104.ccbb.ballot_box.repo.BallotBoxRepo;
import com.D104.ccbb.user.repository.UserRepository;
import com.D104.ccbb.vote.repo.VoteRepo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class BallotBoxService {

	private final BallotBoxRepo ballotBoxRepo;
	private final UserRepository userRepository;
	private final VoteRepo voteRepo;

	public void setBallotBox(BallotBoxDto ballotBoxDto) {
		BallotBox ballotBox = BallotBox.builder()
			.ballotBoxId(ballotBoxDto.getBallotBoxId())
			.pick(ballotBoxDto.getPick())
			.userId(userRepository.getReferenceById(ballotBoxDto.getUserId()))
			.vote(voteRepo.getReferenceById(ballotBoxDto.getVoteId()))
			.build();
		ballotBoxRepo.save(ballotBox);
	}

	public BallotResultDto getBallotResult(int voteId) {
		BallotResultDto ballotResultDto = new BallotResultDto();
		ballotResultDto.setPick1(ballotBoxRepo.countByVote_VoteIdAndPick(voteId, 1));
		ballotResultDto.setPick2(ballotBoxRepo.countByVote_VoteIdAndPick(voteId, 2));
		return ballotResultDto;
	}

	public List<BallotBox> getBallotList(int voteId){
		return ballotBoxRepo.findByVote_VoteId(voteId);
	}

}
