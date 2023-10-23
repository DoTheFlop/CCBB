package com.D104.ccbb.goods.dto;

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
}
