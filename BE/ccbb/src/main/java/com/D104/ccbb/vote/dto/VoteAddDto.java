package com.D104.ccbb.vote.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VoteAddDto {
	private String argument;
	private Boolean accept1;
	private Boolean accept2;
	private LocalDateTime voteStart;
	private LocalDateTime deadline;
	private String tier;
	private String promise;
	private Integer deposit;
	private Integer userId1;
	private String userId2;
	private Integer postId;
	private Boolean doPromise;
	private Integer selectLine;
	// public static VoteAddDto fromEntity(Vote vote) {
	// 	return VoteAddDto.builder()
	// 		.voteId(vote.getVoteId())
	// 		.vote1(vote.getVote1())
	// 		.vote2(vote.getVote2())
	// 		.argument(vote.getArgument())
	// 		.accept1(vote.getAccept1())
	// 		.accept2(vote.getAccept2())
	// 		.voteStart(vote.getVoteStart())
	// 		.deadline(vote.getDeadline())
	// 		.tier(vote.getTier())
	// 		.promise(vote.getPromise())
	// 		.deposit(vote.getDeposit())
	// 		.userId1(vote.getUserId1().getUserId())
	// 		.userId2(vote.getUserId2().getUserId())
	// 		.postId(vote.getPostId().getPostId())
	// 		.build();
	// }
}
