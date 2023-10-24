package com.D104.ccbb.goods.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.D104.ccbb.goods.dto.GoodsDto;
import com.D104.ccbb.goods.service.GoodsService;

@RestController
@RequestMapping("event/goods")
public class GoodsController {

	@Autowired
	private GoodsService goodsService;

	@PostMapping("/add")
	public ResponseEntity<Map<String, Object>> add(@RequestBody GoodsDto goodsDto) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			goodsService.setGoods(goodsDto);
			resultMap.put("message", "success");
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			resultMap.put("messege", "fail: " + e.getClass().getSimpleName());
			System.out.println(e);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@GetMapping("/list")
	public ResponseEntity<Map<String, Object>> add(@RequestParam int eventId) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			List<GoodsDto> goodsList = goodsService.getList(eventId)
				.stream()
				.map(m -> GoodsDto.fromEntity(m))
				.collect(Collectors.toList());
			resultMap.put("goodsList", goodsList);
			resultMap.put("message", "success");
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			//            logger.error("질문 검색 실패", e);
			resultMap.put("message", "fail: " + e.getClass().getSimpleName());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

}
