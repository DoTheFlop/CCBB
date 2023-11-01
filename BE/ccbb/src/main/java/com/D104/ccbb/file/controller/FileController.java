package com.D104.ccbb.file.controller;

import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

	@GetMapping("/get/{fileId}")
	public ResponseEntity<FileSystemResource> getVideoFile(@PathVariable int fileId) {
		try {
			log.info("fileController: {}", fileId);
			ResponseEntity<FileSystemResource> file = fileService.getFile(fileId);
			log.info("file: {}", file);
			return file;
		} catch (Exception e) {
			log.error(e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
