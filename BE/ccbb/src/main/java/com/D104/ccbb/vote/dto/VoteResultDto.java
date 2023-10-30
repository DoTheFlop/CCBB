package com.D104.ccbb.vote.dto;

import com.D104.ccbb.ballot_box.domain.BallotBox;
import com.D104.ccbb.ballot_box.repo.BallotBoxRepo;
import com.D104.ccbb.vote.domain.Vote;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VoteResultDto {

	private Integer voteId;
	private Boolean win;

	// public static VoteResultDto fromEntity(Vote vote){
	// 	BallotBoxRepo ballotBoxRepo = null;
	// 	Boolean a;
	// 	if(ballotBoxRepo.countByVote_VoteIdAndPick(vote.getVoteId(), 1)>)
	// 	return VoteResultDto.builder()
	// 		.voteId(vote.getVoteId())
	// 		.win()
	// 		.build();
	// }

}
