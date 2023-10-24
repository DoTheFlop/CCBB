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
	private Integer count;
	private Integer price;
	private Integer eventId;

	public static GoodsDto fromEntity(Goods goods) {
		return GoodsDto.builder()
			.goodsId(goods.getGoodsId())
			.name(goods.getName())
			.count(goods.getCount())
			.price(goods.getPrice())
			.eventId(goods.getEventId().getEventId())
			.build();
	}
}
