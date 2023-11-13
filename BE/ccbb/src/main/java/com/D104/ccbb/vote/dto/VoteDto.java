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
	private String limitTier;
	private String promise;
	private Integer deposit;
	private String nickname1;
	private String nickname2;
	private Integer user1;
	private Integer user2;
	private String tier1;
	private String tier2;
	private Byte position1;
	private Byte position2;
	private Integer postId;
	private Boolean doPromise;

	public static VoteDto fromEntity(Vote vote) {
		return VoteDto.builder()
			.voteId(vote.getVoteId())
			.argument(vote.getArgument())
			.accept1(vote.getAccept1())
			.accept2(vote.getAccept2())
			.voteStart(vote.getVoteStart())
			.deadline(vote.getDeadline())
			.limitTier(vote.getTier())
			.promise(vote.getPromise())
			.deposit(vote.getDeposit())
			.nickname1(vote.getUserId1().getNickname())
			.nickname2(vote.getUserId2().getNickname())
			.user1(vote.getUserId1().getUserId())
			.user2(vote.getUserId2().getUserId())
			.position1(vote.getUserId1().getMainPosition())
			.position2(vote.getUserId2().getMainPosition())
			.tier1(vote.getUserId1().getLol())
			.tier2(vote.getUserId2().getLol())
			.doPromise(vote.getDoPromise())
			.postId(vote.getPostId().getPostId())
			.build();
	}
}
