package com.D104.ccbb.post.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.D104.ccbb.file.dto.FileDto;
import com.D104.ccbb.post.domain.Post;

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
	private Integer type;
	private String userEmail;
	private List<FileDto> fileId;

	public static PostLoadDto fromEntity(Post post) {
		return PostLoadDto.builder()
			.postId(post.getPostId())
			.title(post.getTitle())
			.content(post.getContent())
			.createDate(post.getCreateDate())
			.type(post.getType())
			.userEmail(post.getUserId().getEmail())
			.fileId(post.getFiles().stream().map(FileDto::fromEntity).collect(Collectors.toList()))
			.build();
	}
}
