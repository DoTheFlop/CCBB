package com.D104.ccbb.wod.dto;

import java.time.LocalDateTime;

import com.D104.ccbb.ballot_box.repo.BallotBoxRepo;
import com.D104.ccbb.post.repo.PostRepo;
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
	private String title;
	private LocalDateTime createTime;
	private Long commentCount;
	private Long userCount;
	public static WodDto fromEntity(Wod wod, PostRepo postRepo, BallotBoxRepo ballotBoxRepo){
		return WodDto.builder()
			.wodId(wod.getWodId())
			.userId(wod.getUserId().getUserId())
			.postId(wod.getPostId().getPostId())
			.title(postRepo.getReferenceById(wod.getPostId().getPostId()).getTitle())
			.createTime(postRepo.getReferenceById(wod.getPostId().getPostId()).getCreateDate())
			.commentCount(postRepo.countUser(wod.getPostId().getPostId(),wod.getPostId().getPostId()))
			.userCount(ballotBoxRepo.countByVote_VoteId(postRepo.getReferenceById(wod.getPostId().getPostId()).getVote().getVoteId()))
			.build();
	}
}
