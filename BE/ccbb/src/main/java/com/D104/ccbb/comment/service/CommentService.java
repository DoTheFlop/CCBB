package com.D104.ccbb.comment.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.D104.ccbb.comment.domain.Comment;
import com.D104.ccbb.comment.dto.CommentDto;
import com.D104.ccbb.comment.dto.CommentRequestDto;
import com.D104.ccbb.comment.repo.CommentRepo;
import com.D104.ccbb.notification.dto.NotificationRequestDto;
import com.D104.ccbb.post.domain.Post;
import com.D104.ccbb.post.repo.PostRepo;
import com.D104.ccbb.user.domain.User;
import com.D104.ccbb.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class CommentService {

	private final CommentRepo commentRepo;
	private final UserRepository userRepository;
	private final PostRepo postRepo;
	private final ApplicationEventPublisher eventPublisher;

	@Transactional
	public void setComment(CommentRequestDto commentRequestDto, User writer) {
		Post post = postRepo.getReferenceById(commentRequestDto.getPostId());

		Comment comment = Comment.builder()
			.content(commentRequestDto.getContent())
			.createDate(LocalDateTime.now())
			.userId(writer)
			.postId(post)
			.build();
		commentRepo.save(comment);

		// 알람 전송
		eventPublisher.publishEvent(NotificationRequestDto.commentOf(post));
	}

	public List<Comment> getComment(int postId) {
		return commentRepo.findByPostId_PostId(postId);
	}

	public void deleteComment(int commentId) {
		commentRepo.deleteById(commentId);
	}

	public void modifyComment(CommentDto commentDto) {
		Comment comment = commentRepo.getReferenceById(commentDto.getCommentId());
		comment.setContent(commentDto.getContent());
		commentRepo.save(comment);
	}

}
