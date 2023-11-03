package com.D104.ccbb.vote.dto;

import org.springframework.beans.factory.annotation.Autowired;

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
	private Integer win;

	public static VoteResultDto fromEntity(Vote vote){
			return VoteResultDto.builder()
				.voteId(vote.getVoteId())
				.win(0)
				.build();
	}

}
