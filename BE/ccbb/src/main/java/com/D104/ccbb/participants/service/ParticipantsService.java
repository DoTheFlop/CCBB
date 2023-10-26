package com.D104.ccbb.participants.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.D104.ccbb.event.repo.EventRepo;
import com.D104.ccbb.goods.repo.GoodsRepo;
import com.D104.ccbb.participants.domain.Participants;
import com.D104.ccbb.participants.dto.ParticipantsDto;
import com.D104.ccbb.participants.repo.ParticipantsRepo;
import com.D104.ccbb.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class ParticipantsService {

	private final ParticipantsRepo participantsRepo;
	private final UserRepository userRepository;
	private final EventRepo eventRepo;
	private final GoodsRepo goodsRepo;

	public void setParticipantsRepo(ParticipantsDto participantsDto) {
		Participants participants = Participants.builder()
			.participantsId(participantsDto.getParticipantsId())
			.createDate(participantsDto.getCreateDate())
			.userId(userRepository.getReferenceById(participantsDto.getUserId()))
			.eventId(eventRepo.getReferenceById(participantsDto.getEventId()))
			.goodsId(goodsRepo.getReferenceById(participantsDto.getGoodsId()))
			.build();
		participantsRepo.save(participants);
	}

}
