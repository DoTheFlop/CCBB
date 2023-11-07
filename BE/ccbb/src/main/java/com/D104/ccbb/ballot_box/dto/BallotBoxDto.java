package com.D104.ccbb.ballot_box.dto;

import com.D104.ccbb.ballot_box.domain.BallotBox;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BallotBoxDto {

	private Integer ballotBoxId;
	private Integer pick;
	private Integer userId;
	private Integer voteId;

	public static BallotBoxDto fromEntity(BallotBox ballotBox){
		return BallotBoxDto.builder()
			.ballotBoxId(ballotBox.getBallotBoxId())
			.pick(ballotBox.getPick())
			.userId(ballotBox.getUserId().getUserId())
			.voteId(ballotBox.getVote().getVoteId())
			.build();
	}

}
