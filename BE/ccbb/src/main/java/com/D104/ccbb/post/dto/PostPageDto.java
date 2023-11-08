package com.D104.ccbb.post.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.D104.ccbb.ballot_box.repo.BallotBoxRepo;
import com.D104.ccbb.file.dto.FileDto;
import com.D104.ccbb.post.domain.Post;
import com.D104.ccbb.user.repository.UserRepository;
import com.D104.ccbb.vote.domain.Vote;
import com.D104.ccbb.vote.repo.VoteRepo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostPageDto {

	private Integer postId;
	private String title;
	private LocalDateTime createDate;
	private Integer type;
	private String nickname;
	private String userEmail;
	private String tier;
	private LocalDateTime deadline;
	private Long voteCount;
	private List<FileDto> fileId;

	public static PostPageDto fromEntity(Post post, Vote vote, BallotBoxRepo ballotBoxRepo) {
		return PostPageDto.builder()
			.postId(post.getPostId())
			.title(post.getTitle())
			.createDate(post.getCreateDate())
			.type(post.getType())
			.nickname(post.getUserId().getNickname())
			.userEmail(post.getUserId().getEmail())
			.tier(vote.getTier())
			.deadline(vote.getDeadline())
			.voteCount(ballotBoxRepo.countByVote_VoteId(vote.getVoteId()))
			.fileId(post.getFiles().stream().map(FileDto::fromEntity).collect(Collectors.toList()))
			.build();
	}

	// public static PostPageDto fromEntity2(Post post, Vote vote, BallotBoxRepo ballotBoxRepo) {
	// 	return PostPageDto.builder()
	// 		.postId(post.getPostId())
	// 		.title(post.getTitle())
	// 		.createDate(post.getCreateDate())
	// 		.type(post.getType())
	// 		.nickname(post.getUserId().getNickname())
	// 		.userEmail(post.getUserId().getEmail())
	// 		.tier(vote.getTier())
	// 		.deadline(vote.getDeadline())
	// 		.voteCount(ballotBoxRepo.countByVote_VoteId(vote.getVoteId()))
	// 		.fileId(post.getFiles().stream().map(FileDto::fromEntity).collect(Collectors.toList()))
	// 		.build();
	// }


}
