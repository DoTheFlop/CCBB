package com.D104.ccbb.comment.service;

import java.util.List;

import com.D104.ccbb.notification.domain.NotificationType;
import com.D104.ccbb.notification.service.NotificationService;
import com.D104.ccbb.post.domain.Post;
import com.D104.ccbb.user.domain.User;
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
	private final NotificationService notificationService;

	@Transactional
	public void setComment(CommentDto commentDto) {
		Post post = postRepo.getReferenceById(commentDto.getPostId());
		User user = userRepository.getReferenceById(commentDto.getUserId());

		Comment comment = Comment.builder()
			.content(commentDto.getContent())
			.createDate(commentDto.getCreateDate())
			.userId(user)
			.postId(post)
			.build();
		commentRepo.save(comment);

		// 알람 전송
		Integer postId = post.getPostId();
		User receiver = post.getUserId();
		String content = post.getTitle();
		if (content.length() > 8) {
			content = content.substring(0, 8) + "...";
		}
		content = user.getNickname() + "님이 회원님이 작성하신 \"" + content + "\"에 답변을 달았습니다.";
		String url = "/lolvote/detail/" + postId;
		notificationService.send(receiver, NotificationType.COMMENT, content, url);

	}

	public List<Comment> getComment(int postId) {
		return commentRepo.findByPostId_PostId(postId);
	}

	public void deleteComment(int commentId) {
		commentRepo.delete(commentRepo.getReferenceById(commentId));
	}

	public void modifyComment(CommentDto commentDto) {
		Comment comment = commentRepo.getReferenceById(commentDto.getCommentId());
		comment.setContent(commentDto.getContent());
		commentRepo.save(comment);
	}

}
