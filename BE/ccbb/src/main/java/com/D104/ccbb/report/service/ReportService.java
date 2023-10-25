package com.D104.ccbb.report.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.D104.ccbb.comment.repo.CommentRepo;
import com.D104.ccbb.post.repo.PostRepo;
import com.D104.ccbb.re_comment.repo.ReCommentRepo;
import com.D104.ccbb.report.domain.Report;
import com.D104.ccbb.report.dto.ReportDto;
import com.D104.ccbb.report.repo.ReportRepo;
import com.D104.ccbb.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class ReportService {

	private final ReportRepo reportRepo;
	private final UserRepository userRepository;
	private final PostRepo postRepo;
	private final CommentRepo commentRepo;
	private final ReCommentRepo reCommentRepo;

	public void setReport(ReportDto reportDto) {
		if (reportDto.getCommentId() != null) {
			Report report = Report.builder()
				.createDate(reportDto.getCreateDate())
				.userId(userRepository.getReferenceById(reportDto.getUserId()))
				.commentId(commentRepo.getReferenceById(reportDto.getCommentId()))
				.build();
			reportRepo.save(report);
		} else if (reportDto.getReCommentId() != null) {
			Report report = Report.builder()
				.createDate(reportDto.getCreateDate())
				.userId(userRepository.getReferenceById(reportDto.getUserId()))
				.reCommentId(reCommentRepo.getReferenceById(reportDto.getReCommentId()))
				.build();
			reportRepo.save(report);
		} else if (reportDto.getPostId() != null) {
			Report report = Report.builder()
				.createDate(reportDto.getCreateDate())
				.userId(userRepository.getReferenceById(reportDto.getUserId()))
				.postId(postRepo.getReferenceById(reportDto.getPostId()))
				.build();
			reportRepo.save(report);
		}
	}

}
