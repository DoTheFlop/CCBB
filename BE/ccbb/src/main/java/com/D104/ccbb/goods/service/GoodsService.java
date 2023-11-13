package com.D104.ccbb.goods.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.D104.ccbb.event.repo.EventRepo;
import com.D104.ccbb.goods.domain.Goods;
import com.D104.ccbb.goods.dto.GoodsDto;
import com.D104.ccbb.goods.repo.GoodsRepo;
import com.D104.ccbb.participants.domain.Participants;
import com.D104.ccbb.participants.repo.ParticipantsRepo;
import com.D104.ccbb.point_history.domain.PointHistory;
import com.D104.ccbb.point_history.repo.PointHistoryRepo;
import com.D104.ccbb.user.domain.User;
import com.D104.ccbb.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
// @Transactional
@RequiredArgsConstructor
public class GoodsService {

	private final GoodsRepo goodsRepo;
	private final EventRepo eventRepo;
	private final UserRepository userRepository;
	private final ParticipantsRepo participantsRepo;
	private final PointHistoryRepo pointHistoryRepo;

	// @Transactional
	// public Goods setGoods(GoodsDto goodsDto) {
	// 	Goods goods = Goods.builder()
	// 		.goodsId(goodsDto.getGoodsId())
	// 		.name(goodsDto.getName())
	// 		.winPercent(goodsDto.getWinPercent())
	// 		.winCount(goodsDto.getWinCount())
	// 		.price(goodsDto.getPrice())
	// 		.eventId(eventRepo.getReferenceById(goodsDto.getEventId()))
	// 		.build();
	// 	Goods save = goodsRepo.save(goods);
	// 	return save;
	// }

	@Transactional(readOnly = false)
	public Goods setGoods(GoodsDto goodsDto) {
		Goods goods = Goods.builder()
			.goodsId(goodsDto.getGoodsId())
			.name(goodsDto.getName())
			.winPercent(goodsDto.getWinPercent())
			.winCount(goodsDto.getWinCount())
			.price(goodsDto.getPrice())
			.eventId(eventRepo.getReferenceById(goodsDto.getEventId()))
			.build();
		Goods savedGoods = goodsRepo.saveAndFlush(goods);
		return savedGoods;
	}

	public List<Goods> getList(int eventId) {
		return goodsRepo.findByEventId_EventId(eventId);
	}

	@Transactional(readOnly = false)
	public String entryGoods(String userEmail, int goodsId) {
		User user = userRepository.findByEmail(userEmail)
			.orElseThrow(() -> new IllegalArgumentException("Invalid userEmail:" + userEmail));
		Goods goods = goodsRepo.findGoodsByGoodsId(goodsId)
			.orElseThrow(() -> new IllegalArgumentException("Invalid goodsId:" + goodsId));

		if (user.getPoint() - goods.getPrice() < 0) {
			throw new IllegalStateException("포인트가 부족합니다.");
		} else {
			user.setPoint(user.getPoint() - goods.getPrice());
		}

		if (goods.getWinCount() <= 0) {
			throw new IllegalStateException("상품 응모가 끝났습니다.");
		}
		pointHistoryRepo.save(PointHistory.builder()
			.value(-goods.getPrice())
			.userId(user)
			.detail("상품 응모")
			.build());
		double randomValue = Math.random() * 100; // 0.0 ~ 99.9 사이의 난수를 생성
		if (randomValue < goods.getWinPercent()) {
			goods.setWinCount(goods.getWinCount() - 1);
			userRepository.save(user);
			goodsRepo.save(goods);
			participantsRepo.save(Participants.builder()
				.goodsId(goods)
				.userId(user)
				.eventId(goods.getEventId())
				.createDate(LocalDateTime.now())
				.build());
			return "응모에 성공했습니다.";
		} else {
			userRepository.save(user);
			goodsRepo.save(goods);
			return "응모에 실패했습니다.";
		}

		// Here you can add logic to handle the entry of the user to the goods.
	}
}
