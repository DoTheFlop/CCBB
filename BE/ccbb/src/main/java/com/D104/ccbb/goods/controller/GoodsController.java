package com.D104.ccbb.goods.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.D104.ccbb.file.service.FileService;
import com.D104.ccbb.goods.domain.Goods;
import com.D104.ccbb.goods.dto.GoodsDto;
import com.D104.ccbb.goods.service.GoodsService;
import com.D104.ccbb.jwt.service.JwtTokenService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("event/goods")
@RequiredArgsConstructor
@Slf4j
public class GoodsController {

	private final GoodsService goodsService;
	private final FileService fileService;
	private final JwtTokenService jwtTokenService;
	@PostMapping("/add")
	public ResponseEntity<Map<String, Object>> add(
		@RequestPart(value = "files", required = false) List<MultipartFile> files,
		@RequestPart(value = "goods") GoodsDto goodsDto) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			Goods goods = goodsService.setGoods(goodsDto);
			if (files != null)
				fileService.saveFile(files, "goods", goods.getGoodsId());

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
			log.error("상품조회 실패: ", e);
			resultMap.put("message", "fail: " + e.getClass().getSimpleName());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@PostMapping("/entry")
	public ResponseEntity<Map<String, Object>> entry(@RequestHeader String Authorization, @RequestParam int goodsId) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			String userEmail = jwtTokenService.getUserEmail(jwtTokenService.extractToken(Authorization));
			String entryResult = goodsService.entryGoods(userEmail, goodsId);
			resultMap.put("message", entryResult);
			resultMap.put("Authorization", Authorization);

			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			resultMap.put("message", "fail: " + e.getMessage());
			resultMap.put("Authorization", Authorization);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

}
