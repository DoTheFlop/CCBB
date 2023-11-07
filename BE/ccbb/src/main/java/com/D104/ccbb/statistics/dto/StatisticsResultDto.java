package com.D104.ccbb.statistics.dto;

import com.D104.ccbb.user.domain.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatisticsResultDto {
	private Integer challenger;
	private Integer challengerVictory;
	private Integer grandMaster;
	private Integer grandMasterVictory;
	private Integer master;
	private Integer masterVictory;
	private Integer diamond;
	private Integer diamondVictory;
	private Integer emerald;
	private Integer emeraldVictory;
	private Integer platinum;
	private Integer platinumVictory;
	private Integer gold;
	private Integer goldVictory;
	private Integer silver;
	private Integer silverVictory;
	private Integer bronze;
	private Integer bronzeVictory;
	private Integer iron;
	private Integer ironVictory;
	private Integer top;
	private Integer topVictory;
	private Integer jungle;
	private Integer jungleVictory;
	private Integer mid;
	private Integer midVictory;
	private Integer adCarry;
	private Integer adCarryVictory;
	private Integer supporter;
	private Integer supporterVictory;

	public static StatisticsResultDto toZero() {
		return StatisticsResultDto.builder()
			.challenger(0)
			.challengerVictory(0)
			.grandMaster(0)
			.grandMasterVictory(0)
			.master(0)
			.masterVictory(0)
			.diamond(0)
			.diamondVictory(0)
			.emerald(0)
			.emeraldVictory(0)
			.platinum(0)
			.platinumVictory(0)
			.gold(0)
			.goldVictory(0)
			.silver(0)
			.silverVictory(0)
			.bronze(0)
			.bronzeVictory(0)
			.iron(0)
			.ironVictory(0)
			.top(0)
			.topVictory(0)
			.jungle(0)
			.jungleVictory(0)
			.mid(0)
			.midVictory(0)
			.adCarry(0)
			.adCarryVictory(0)
			.supporter(0)
			.supporterVictory(0)
			.build();
	}
}
