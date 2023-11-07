package com.D104.ccbb.vote.dto;

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
public class VoteAcceptDto {

	private String email;
	private String nickname;
	private String title;
	private Integer postId;

	public static VoteAcceptDto fromEntity(Vote vote){
		return VoteAcceptDto.builder()
			.email(vote.getUserId1().getEmail())
			.nickname(vote.getUserId1().getNickname())
			.title(vote.getPostId().getTitle())
			.postId(vote.getPostId().getPostId())
			.build();
	}
}
