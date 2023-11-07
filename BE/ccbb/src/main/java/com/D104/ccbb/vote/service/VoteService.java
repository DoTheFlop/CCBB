package com.D104.ccbb.vote.service;

import java.time.LocalDateTime;
import java.util.List;

import com.D104.ccbb.notification.dto.NotificationRequestDto;
import com.D104.ccbb.post.domain.Post;
import com.D104.ccbb.user.domain.User;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.D104.ccbb.post.repo.PostRepo;
import com.D104.ccbb.user.repository.UserRepository;
import com.D104.ccbb.vote.domain.Vote;
import com.D104.ccbb.vote.dto.VoteAddDto;
import com.D104.ccbb.vote.repo.VoteRepo;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class VoteService {

	private final VoteRepo voteRepo;
	private final UserRepository userRepository;
	private final PostRepo postRepo;
	private final ApplicationEventPublisher eventPublisher;

	@Transactional
	public void setVote(VoteAddDto voteAddDto) {
		Post post = postRepo.getReferenceById(voteAddDto.getPostId());
		User producer = userRepository.getReferenceById(voteAddDto.getUserId1());
		User receiver = userRepository.findByEmail(voteAddDto.getUserId2()).get();

		Vote vote = Vote.builder()
			.voteId(voteAddDto.getVoteId())
			.argument(voteAddDto.getArgument())
			.accept1(voteAddDto.getAccept1())
			.accept2(voteAddDto.getAccept2())
			.voteStart(voteAddDto.getVoteStart())
			.deadline(voteAddDto.getDeadline())
			.tier(voteAddDto.getTier())
			.promise(voteAddDto.getPromise())
			.deposit(voteAddDto.getDeposit())
			.userId1(producer)
			.userId2(receiver)
			.postId(post)
			.build();

		voteRepo.save(vote);

		eventPublisher.publishEvent(NotificationRequestDto.voteRequestOf(post, receiver));
	}

	public List<Vote> getVoteList(LocalDateTime deadLine){
		return voteRepo.findByDeadlineLessThan(deadLine);
	}



}
