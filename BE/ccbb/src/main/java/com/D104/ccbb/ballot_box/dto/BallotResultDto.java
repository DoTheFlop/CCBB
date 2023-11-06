package com.D104.ccbb.ballot_box.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BallotResultDto {

	private Long pick1;
	private Long pick2;
	private Integer userPick;
}
