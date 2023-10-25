package com.D104.ccbb.comment.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.D104.ccbb.comment.domain.Comment;
import com.D104.ccbb.comment.dto.CommentDto;
import com.D104.ccbb.comment.repo.CommentRepo;
import com.D104.ccbb.post.repo.PostRepo;
import com.D104.ccbb.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {

	private final CommentRepo commentRepo;
	private final UserRepository userRepository;
	private final PostRepo postRepo;

	@Transactional
	public void setComment(CommentDto commentDto) {
		Comment comment = Comment.builder()
			.content(commentDto.getContent())
			.createDate(commentDto.getCreateDate())
			.userId(userRepository.getReferenceById(commentDto.getUserId()))
			.postId(postRepo.getReferenceById(commentDto.getPostId()))
			.build();
		commentRepo.save(comment);
	}

	public List<Comment> getComment(int postId) {
		return commentRepo.findByPostId_PostId(postId);
	}

	public void deleteComment(int commentId) {
		commentRepo.delete(commentRepo.getReferenceById(commentId));
	}
}
