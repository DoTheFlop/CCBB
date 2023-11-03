// package com.D104.ccbb.user.dto;
//
// import java.util.List;
// import java.util.stream.Collectors;
//
// import com.D104.ccbb.comment.domain.Comment;
// import com.D104.ccbb.comment.dto.CommentGetDto;
// import com.D104.ccbb.re_comment.dto.ReCommentGetDto;
// import com.D104.ccbb.vote.domain.Vote;
//
// import lombok.AllArgsConstructor;
// import lombok.Builder;
// import lombok.Data;
// import lombok.NoArgsConstructor;
//
// @Builder
// @Data
// @AllArgsConstructor
// @NoArgsConstructor
// public class UserDetailDto {
//
// 	private String tier;
// 	private Byte position;
// 	private String nickname;
//
// 	public static UserDetailDto fromEntity(Vote vote) {
// 		List<ReCommentGetDto> reCommentGetDto = comment.getReComment()
// 			.stream()
// 			.map(m -> ReCommentGetDto.fromEntity(m))
// 			.collect(Collectors.toList());
// 		return CommentGetDto.builder()
// 			.tier(vote.getUserId().getLol())
// 			.position(comment.getUserId().getMainPosition())
// 			.nickname(comment.getUserId().getNickname())
// 			.postId(comment.getPostId().getPostId())
// 			.reComment(reCommentGetDto)
// 			.build();
// 	}
//
// }
