package com.D104.ccbb.vote.dto;

import java.time.LocalDateTime;

import com.D104.ccbb.vote.domain.Vote;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VoteDto {
	private Integer voteId;
	private String argument;
	private Boolean accept1;
	private Boolean accept2;
	private LocalDateTime voteStart;
	private LocalDateTime deadline;
	private String tier;
	private String promise;
	private Integer deposit;
	private Integer userId1;
	private Integer userId2;
	private Integer postId;

	public static VoteDto fromEntity(Vote vote) {
		return VoteDto.builder()
			.voteId(vote.getVoteId())
			.argument(vote.getArgument())
			.accept1(vote.getAccept1())
			.accept2(vote.getAccept2())
			.voteStart(vote.getVoteStart())
			.deadline(vote.getDeadline())
			.tier(vote.getTier())
			.promise(vote.getPromise())
			.deposit(vote.getDeposit())
			.userId1(vote.getUserId1().getUserId())
			.userId2(vote.getUserId2().getUserId())
			.postId(vote.getPostId().getPostId())
			.build();
	}
}
