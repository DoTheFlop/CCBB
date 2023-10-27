package com.D104.ccbb.goods.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.D104.ccbb.event.repo.EventRepo;
import com.D104.ccbb.goods.domain.Goods;
import com.D104.ccbb.goods.dto.GoodsDto;
import com.D104.ccbb.goods.repo.GoodsRepo;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class GoodsService {

	private final GoodsRepo goodsRepo;
	private final EventRepo eventRepo;

	public Goods setGoods(GoodsDto goodsDto) {
		Goods goods = Goods.builder()
			.goodsId(goodsDto.getGoodsId())
			.name(goodsDto.getName())
			.count(goodsDto.getCount())
			.price(goodsDto.getPrice())
			.eventId(eventRepo.getReferenceById(goodsDto.getEventId()))
			.build();
		Goods save = goodsRepo.save(goods);
		return save;
	}

	public List<Goods> getList(int eventId) {
		return goodsRepo.findByEventId_EventId(eventId);
	}

}
