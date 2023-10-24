package com.D104.ccbb.post.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.D104.ccbb.post.domain.Post;
import com.D104.ccbb.post.dto.PostDto;
import com.D104.ccbb.post.repo.PostRepo;
import com.D104.ccbb.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class PostService {

	private final PostRepo postRepo;
	private final UserRepository userRepository;

	public void setPost(PostDto postDto) {

		Post post = Post.builder()
			.postId(postDto.getPostId())
			.title(postDto.getTitle())
			.content(postDto.getContent())
			.createDate(postDto.getCreateDate())
			.type(postDto.getType())
			.userId(userRepository.getReferenceById(postDto.getUserId()))
			.build();
		postRepo.save(post);
	}

	public List<Post> getFree() {
		return postRepo.findByTypeOrderByPostIdDesc(0);
	}

	public List<Post> getVote() {
		return postRepo.findByTypeOrderByPostIdDesc(1);
	}
}
