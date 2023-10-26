package com.D104.ccbb.file.controller;

import org.springframework.core.io.support.ResourceRegion;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.D104.ccbb.file.service.FileService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/file")
@RequiredArgsConstructor
public class FileController {
	private final FileService fileService;

	@GetMapping("/image/{fileName}")
	public ResponseEntity<byte[]> getImageFile(@PathVariable String fileName) {
		try {
			byte[] imageFile = fileService.getImageFile(fileName);
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.IMAGE_JPEG);
			return new ResponseEntity<>(imageFile, headers, HttpStatus.OK);
		} catch (Exception e) {
			log.error(e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/video/{fileName}")
	public ResponseEntity<ResourceRegion> getVideoFile(@PathVariable String fileName,
		@RequestHeader HttpHeaders headers) {
		try {
			log.info("fileName: {}", fileName);
			ResponseEntity<ResourceRegion> videoFile = fileService.getVideoFile(fileName, headers);
			log.info("videoFile: {}", videoFile);
			return videoFile;
		} catch (Exception e) {
			log.error(e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
