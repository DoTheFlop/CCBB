package com.D104.ccbb.vote.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VoteModifyDto {

	private Integer postId;
	private Boolean accept2;
	private Boolean doPromise;

}
