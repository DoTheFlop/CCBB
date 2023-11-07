package com.D104.ccbb.post.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.D104.ccbb.comment.domain.Comment;
import com.D104.ccbb.comment.dto.CommentGetDto;
import com.D104.ccbb.file.dto.FileDto;
import com.D104.ccbb.post.domain.Post;
import com.D104.ccbb.re_comment.dto.ReCommentGetDto;
import com.D104.ccbb.user.domain.User;
import com.D104.ccbb.user.repository.UserRepository;
import com.D104.ccbb.vote.domain.Vote;
import com.D104.ccbb.vote.dto.VoteDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostLoadDto {
	private Integer postId;
	private String title;
	private String content;
	private LocalDateTime createDate;
	private List<CommentGetDto> comment;
	private Integer type;
	private List<FileDto> fileId;
	private VoteDto vote;
	public static PostLoadDto fromEntity(Post post) {
		List<CommentGetDto> commentGetDto = post.getComment()
			.stream()
			.map(m -> CommentGetDto.fromEntity(m))
			.collect(Collectors.toList());
		VoteDto voteDto = VoteDto.fromEntity(post.getVote());
		return PostLoadDto.builder()
			.postId(post.getPostId())
			.title(post.getTitle())
			.content(post.getContent())
			.createDate(post.getCreateDate())
			.comment(commentGetDto)
			.type(post.getType())
			.vote(voteDto)
			.fileId(post.getFiles().stream().map(FileDto::fromEntity).collect(Collectors.toList()))
			.build();
	}
}
