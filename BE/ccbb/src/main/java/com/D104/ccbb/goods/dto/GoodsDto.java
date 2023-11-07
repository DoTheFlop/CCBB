package com.D104.ccbb.goods.dto;

import com.D104.ccbb.goods.domain.Goods;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoodsDto {
	private Integer goodsId;
	private String name;
	private Integer winPercent;
	private Integer winCount;
	private Integer price;
	private Integer eventId;

	public static GoodsDto fromEntity(Goods goods) {

		Integer eventId = null;
		if (goods.getEventId() != null) {
			eventId = goods.getEventId().getEventId();
		}
		return GoodsDto.builder()
			.goodsId(goods.getGoodsId())
			.name(goods.getName())
			.winPercent(goods.getWinPercent())
			.winCount(goods.getWinCount())
			.price(goods.getPrice())
			.eventId(eventId)
			.build();
	}
}
