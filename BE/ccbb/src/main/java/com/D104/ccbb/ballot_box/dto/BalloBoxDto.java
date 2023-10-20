package com.D104.ccbb.ballot_box.dto;

import com.D104.ccbb.user.domain.User;
import com.D104.ccbb.vote.domain.Vote;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BalloBoxDto {

	private Integer ballotBoxId;
	private Integer pick;
	private User userId;
	private Vote voteId;

}
