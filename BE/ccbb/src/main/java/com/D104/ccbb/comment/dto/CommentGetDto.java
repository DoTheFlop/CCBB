package com.D104.ccbb.comment.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.D104.ccbb.ballot_box.repo.BallotBoxRepo;
import com.D104.ccbb.comment.domain.Comment;
import com.D104.ccbb.re_comment.dto.ReCommentGetDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentGetDto {

	private Integer commentId;
	private String content;
	private LocalDateTime createDate;
	private String tier;
	private Byte position;
	private String nickname;
	private Integer userId;
	private Integer postId;
	private Integer pick;
	private List<ReCommentGetDto> reComment;

	public static CommentGetDto fromEntity(Comment comment, BallotBoxRepo ballotBoxRepo) {
		List<ReCommentGetDto> reCommentGetDto = comment.getReComment()
			.stream()
			.map(m -> ReCommentGetDto.fromEntity(m))
			.collect(Collectors.toList());
		if(ballotBoxRepo.findByVote_VoteIdAndUserId_UserId(comment.getPostId().getVote().getVoteId(),comment.getUserId().getUserId())!=null){
			return CommentGetDto.builder()
				.commentId(comment.getCommentId())
				.content(comment.getContent())
				.createDate(comment.getCreateDate())
				.tier(comment.getUserId().getLol())
				.position(comment.getUserId().getMainPosition())
				.nickname(comment.getUserId().getNickname())
				.userId(comment.getUserId().getUserId())
				.postId(comment.getPostId().getPostId())
				.pick(ballotBoxRepo.findByVote_VoteIdAndUserId_UserId(comment.getPostId().getVote().getVoteId(),comment.getUserId().getUserId()).getPick())
				.reComment(reCommentGetDto)
				.build();
		}
		else{
			return CommentGetDto.builder()
				.commentId(comment.getCommentId())
				.content(comment.getContent())
				.createDate(comment.getCreateDate())
				.tier(comment.getUserId().getLol())
				.position(comment.getUserId().getMainPosition())
				.nickname(comment.getUserId().getNickname())
				.userId(comment.getUserId().getUserId())
				.postId(comment.getPostId().getPostId())
				.pick(null)
				.reComment(reCommentGetDto)
				.build();
		}
	}

}
