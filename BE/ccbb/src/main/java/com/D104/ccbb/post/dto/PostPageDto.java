package com.D104.ccbb.post.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.D104.ccbb.file.dto.FileDto;
import com.D104.ccbb.post.domain.Post;
import com.D104.ccbb.user.repository.UserRepository;

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
	private List<FileDto> fileId;

	public static PostPageDto fromEntity(Post post) {
		return PostPageDto.builder()
			.postId(post.getPostId())
			.title(post.getTitle())
			.createDate(post.getCreateDate())
			.type(post.getType())
			.nickname(post.getUserId().getNickname())
			.userEmail(post.getUserId().getEmail())
			.fileId(post.getFiles().stream().map(FileDto::fromEntity).collect(Collectors.toList()))
			.build();
	}

}
