package com.D104.ccbb.statistics.dto;

import com.D104.ccbb.user.domain.User;
import com.D104.ccbb.user.dto.UserDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatisticsDto {
	private String lol;
	private Byte mainPosition;
	private Integer voteCount;
	private Integer voteVictory;

	public static StatisticsDto fromEntity(User user) {
		return StatisticsDto.builder()
			.lol(user.getLol())
			.mainPosition(user.getMainPosition())
			.voteCount(user.getVoteCount())
			.voteVictory(user.getVoteVictory())
			.build();
	}
}
