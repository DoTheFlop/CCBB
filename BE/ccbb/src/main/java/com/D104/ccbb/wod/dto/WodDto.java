package com.D104.ccbb.wod.dto;

import com.D104.ccbb.wod.domian.Wod;

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
	private Integer postId;

	public static WodDto fromEntity(Wod wod){
		return WodDto.builder()
			.wodId(wod.getWodId())
			.userId(wod.getUserId().getUserId())
			.postId(wod.getPostId().getPostId())
			.build();
	}
}
