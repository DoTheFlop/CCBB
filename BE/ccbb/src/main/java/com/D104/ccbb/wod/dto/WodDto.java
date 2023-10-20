package com.D104.ccbb.wod.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WodDto {
	private Integer wodId;
	private Integer userId;
	private Integer voteId;
}
