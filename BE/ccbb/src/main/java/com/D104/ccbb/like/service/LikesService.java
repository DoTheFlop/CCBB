package com.D104.ccbb.like.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.D104.ccbb.comment.repo.CommentRepo;
import com.D104.ccbb.like.domain.Likes;
import com.D104.ccbb.like.dto.LikesDto;
import com.D104.ccbb.like.repo.LikesRepo;
import com.D104.ccbb.post.repo.PostRepo;
import com.D104.ccbb.re_comment.repo.ReCommentRepo;
import com.D104.ccbb.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class LikesService {

	private final LikesRepo likesRepo;
	private final UserRepository userRepository;
	private final CommentRepo commentRepo;
	private final ReCommentRepo reCommentRepo;
	private final PostRepo postRepo;

	public void setPostLike(LikesDto likesDto) {
		Likes likes = Likes.builder()
			.type(likesDto.getType())
			.createDate(likesDto.getCreateDate())
			.userId(userRepository.getReferenceById(likesDto.getUserId()))
			.postId(postRepo.getReferenceById(likesDto.getPostId()))
			.build();
		log.info(likes.toString());

		likesRepo.save(likes);
	}

	public void setCommentLike(LikesDto likesDto) {
		Likes likes = Likes.builder()
			.type(likesDto.getType())
			.createDate(likesDto.getCreateDate())
			.commentId(commentRepo.getReferenceById(likesDto.getCommentId()))
			.postId(postRepo.getReferenceById(likesDto.getPostId()))
			.build();
		log.info(likes.toString());

		likesRepo.save(likes);
	}

	public void setReCommentLike(LikesDto likesDto) {
		Likes likes = Likes.builder()
			.type(likesDto.getType())
			.createDate(likesDto.getCreateDate())
			.reCommentId(reCommentRepo.getReferenceById(likesDto.getReCommentId()))
			.postId(postRepo.getReferenceById(likesDto.getPostId()))
			.build();
		log.info(likes.toString());

		likesRepo.save(likes);
	}

	public void deleteLikes(int likesId) {
		likesRepo.delete(likesRepo.getReferenceById(likesId));
	}

}
