package com.D104.ccbb.vote.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.D104.ccbb.post.repo.PostRepo;
import com.D104.ccbb.user.repository.UserRepository;
import com.D104.ccbb.vote.domain.Vote;
import com.D104.ccbb.vote.dto.VoteAddDto;
import com.D104.ccbb.vote.dto.VoteModifyDto;
import com.D104.ccbb.vote.repo.VoteRepo;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class VoteService {

	private final VoteRepo voteRepo;
	private final UserRepository userRepository;
	private final PostRepo postRepo;

	@Transactional
	public void setVote(VoteAddDto voteAddDto) {
		Vote vote = Vote.builder()
			.argument(voteAddDto.getArgument())
			.accept1(voteAddDto.getAccept1())
			.accept2(voteAddDto.getAccept2())
			.voteStart(voteAddDto.getVoteStart())
			.deadline(voteAddDto.getDeadline())
			.tier(voteAddDto.getTier())
			.promise(voteAddDto.getPromise())
			.deposit(voteAddDto.getDeposit())
			.userId1(userRepository.getReferenceById(voteAddDto.getUserId1()))
			.userId2(userRepository.findByEmail(voteAddDto.getUserId2()).get())
			.postId(postRepo.getReferenceById(voteAddDto.getPostId()))
			.doPromise(voteAddDto.getDoPromise())
			.selectLine(voteAddDto.getSelectLine())
			.build();

		voteRepo.save(vote);
	}

	public List<Vote> getVoteList(LocalDateTime deadLine){
		return voteRepo.findByDeadlineLessThan(deadLine);
	}

	@Transactional
	public void deleteVote(int voteId){
		voteRepo.delete(voteRepo.getReferenceById(voteId));
	}

	@Transactional
	public void modifyVote(VoteModifyDto voteModifyDto){
		Vote vote = voteRepo.getReferenceById(voteModifyDto.getVoteId());
		if(voteModifyDto.getAccept2()!=null)
			vote.setAccept2(voteModifyDto.getAccept2());
		if(voteModifyDto.getDoPromise()!=null)
			vote.setDoPromise(voteModifyDto.getDoPromise());
		voteRepo.save(vote);
	}

}
