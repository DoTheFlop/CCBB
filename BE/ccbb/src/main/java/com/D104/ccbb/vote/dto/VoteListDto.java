package com.D104.ccbb.vote.dto;

import java.time.LocalDateTime;

import com.D104.ccbb.ballot_box.repo.BallotBoxRepo;
import com.D104.ccbb.comment.repo.CommentRepo;
import com.D104.ccbb.post.repo.PostRepo;
import com.D104.ccbb.vote.domain.Vote;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VoteListDto {

	private LocalDateTime createDate;
	private String title;
	private Integer postId;
	private Long commentCount;
	private Long userCount;

	public static VoteListDto fromEntity(Vote vote, CommentRepo commentRepo, BallotBoxRepo ballotBoxRepo){
		// BallotBoxRepo ballotBoxRepo = null;
		// CommentRepo commentRepo = null;
		return VoteListDto.builder()
			.createDate(vote.getPostId().getCreateDate())
			.title(vote.getPostId().getTitle())
			.postId(vote.getPostId().getPostId())
			.commentCount(commentRepo.countByPostId_PostId(vote.getPostId().getPostId()))
			.userCount(ballotBoxRepo.countByVote_VoteId(vote.getVoteId()))
			.build();
	}


}
