package com.D104.ccbb.re_comment.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.D104.ccbb.comment.repo.CommentRepo;
import com.D104.ccbb.re_comment.domain.ReComment;
import com.D104.ccbb.re_comment.dto.ReCommentDto;
import com.D104.ccbb.re_comment.repo.ReCommentRepo;
import com.D104.ccbb.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class ReCommentService {

	private final ReCommentRepo reCommentRepo;
	private final CommentRepo commentRepo;
	private final UserRepository userRepository;

	public void setReCommentRepo(ReCommentDto reCommentDto) {
		ReComment reComment = ReComment.builder()
			.content(reCommentDto.getContent())
			.createDate(reCommentDto.getCreateDate())
			.userId(userRepository.getReferenceById(reCommentDto.getUserId()))
			.commentId(commentRepo.getReferenceById(reCommentDto.getCommentId()))
			.build();
		reCommentRepo.save(reComment);
	}

	public List<ReComment> getReComment(int commentId) {
		return reCommentRepo.findByCommentId_CommentId(commentId);
	}

	public void deleteReComment(int reCommentId) {
		reCommentRepo.delete(reCommentRepo.getReferenceById(reCommentId));
	}

}
