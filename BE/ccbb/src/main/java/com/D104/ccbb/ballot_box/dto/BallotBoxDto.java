package com.D104.ccbb.ballot_box.dto;

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

}
