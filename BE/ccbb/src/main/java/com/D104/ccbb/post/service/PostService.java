package com.D104.ccbb.post.service;

import java.time.LocalDateTime;
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

	public Post setPost(PostDto postDto) {

		Post post = Post.builder()
			.title(postDto.getTitle())
			.content(postDto.getContent())
			.type(postDto.getType())
			.userId(userRepository.getReferenceById(postDto.getUserId()))
			.build();
		Post save = postRepo.save(post);
		return save;
	}

	public List<Post> getFree() {
		return postRepo.findByTypeOrderByPostIdDesc(0);
	}

	public List<Post> getVote() {
		return postRepo.findByTypeOrderByPostIdDesc(1);
	}

	public void deletePost(int postId) {
		postRepo.delete(postRepo.getReferenceById(postId));
	}

	public void modifyPost(PostDto postDto) {
		Post post = postRepo.getReferenceById(postDto.getPostId());
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		postRepo.save(post);
	}

}
