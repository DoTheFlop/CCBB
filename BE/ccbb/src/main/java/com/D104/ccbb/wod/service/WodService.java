package com.D104.ccbb.wod.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.D104.ccbb.ballot_box.repo.BallotBoxRepo;
import com.D104.ccbb.post.repo.PostRepo;
import com.D104.ccbb.user.repository.UserRepository;
import com.D104.ccbb.vote.domain.Vote;
import com.D104.ccbb.vote.repo.VoteRepo;
import com.D104.ccbb.wod.domian.Wod;
import com.D104.ccbb.wod.dto.WodDto;
import com.D104.ccbb.wod.repo.WodRepo;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class WodService {

	private final UserRepository userRepository;
	private final PostRepo postRepo;
	private final WodRepo wodRepo;
	private final BallotBoxRepo ballotBoxRepo;
	@Transactional
	public void setWod(int userId, int postId){
		Wod wod = Wod.builder()
			.userId(userRepository.getReferenceById(userId))
			.postId(postRepo.getReferenceById(postId))
			.build();
		wodRepo.save(wod);
	}
	public List<WodDto> getWod(int userId){
		List<WodDto> wodDto = wodRepo.findByUserId_UserId(userId).stream().map(m->WodDto.fromEntity(m,postRepo,ballotBoxRepo)).collect(
			Collectors.toList());
		return wodDto;
	}

	public void deleteWod(int userId, int postId){
		wodRepo.delete(wodRepo.findByUserId_UserIdAndPostId_PostId(userId,postId));
	}

	public Boolean wodCheck(int userId, int postId){
		if(wodRepo.findByUserId_UserIdAndPostId_PostId(userId,postId)!=null)
			return true;
		else return false;
	}

}
