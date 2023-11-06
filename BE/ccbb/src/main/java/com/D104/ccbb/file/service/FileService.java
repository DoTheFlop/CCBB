package com.D104.ccbb.file.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.MediaTypeFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.D104.ccbb.event.domain.Event;
import com.D104.ccbb.event.repo.EventRepo;
import com.D104.ccbb.file.domain.File;
import com.D104.ccbb.file.repo.FileRepo;
import com.D104.ccbb.goods.domain.Goods;
import com.D104.ccbb.goods.repo.GoodsRepo;
import com.D104.ccbb.post.domain.Post;
import com.D104.ccbb.post.repo.PostRepo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class FileService {
	private final FileRepo fileRepo;
	private final PostRepo postRepo;
	private final EventRepo eventRepo;
	private final GoodsRepo goodsRepo;

	@Value("${spring.servlet.multipart.location}")
	private String FILE_PATH;

	@Value("${file.video}")
	private String VIDEO_PATH;

	@Value("${file.image}")
	private String IMAGE_PATH;

	@Value("${file.replay}")
	private String REPLAY_PATH;

	// 파일 이름과 확장자를 분리하여 파일 이름 반환
	private String getFileNameWithoutExtension(String filename) {
		int lastDotIndex = filename.lastIndexOf(".");
		if (lastDotIndex != -1) {
			return filename.substring(0, lastDotIndex);
		}
		return filename;
	}

	// 파일의 확장자 반환
	private String getFileExtension(String filename) {
		int lastDotIndex = filename.lastIndexOf(".");
		if (lastDotIndex != -1 && lastDotIndex < filename.length() - 1) {
			return filename.substring(lastDotIndex + 1);
		}
		return "";
	}

	public ResponseEntity<FileSystemResource> getFile(int fileId) throws IOException {
		log.info("fileService fileId : {}", fileId);
		Optional<File> fileOpt = fileRepo.findById(fileId);

		if (fileOpt.isEmpty()) {
			throw new FileNotFoundException("존재하지 않는 파일입니다.");
		}
		File file = fileOpt.get();
		String path = FILE_PATH;
		HttpHeaders headers = new HttpHeaders();
		if (file.getType().startsWith("image")) {
			log.info("이미지 파일");
			path += IMAGE_PATH;
			headers.setContentDispositionFormData("inline", file.getOrgName() + "." + file.getExtension());

		}
		if (file.getType().startsWith("video")) {
			log.info("비디오 파일");
			path += VIDEO_PATH;
			headers.setContentDispositionFormData("inline", file.getOrgName() + "." + file.getExtension());

		}
		if (file.getType().startsWith("application")) {
			log.info("리플레이 파일");
			path += REPLAY_PATH;
			headers.setContentDispositionFormData("attachment", file.getOrgName() + "." + file.getExtension());
		}
		path = path + "/" + file.getName() + "." + file.getExtension();
		log.info("path: {}", path);
		FileSystemResource video = new FileSystemResource(path);
		headers.setContentType(MediaTypeFactory.getMediaType(video).orElse(MediaType.APPLICATION_OCTET_STREAM));
		return new ResponseEntity<>(video, headers, HttpStatus.OK);
	}

	public boolean saveReplayFile(MultipartFile replay, int id) throws Exception {
		String fileExtension = getFileExtension(replay.getOriginalFilename());
		String fileNameWithoutExtension = getFileNameWithoutExtension(replay.getOriginalFilename());
		String contentType = replay.getContentType();
		log.info("content type : {}", contentType);
		String uuidName = UUID.randomUUID().toString();
		File build = File.builder()
			.orgName(fileNameWithoutExtension)
			.name(uuidName)
			.extension(fileExtension)
			.type(contentType)
			.isPromise(false)
			.build();
		Optional<Post> byId = postRepo.findById(id);
		Post post = byId.orElseThrow(() -> new Exception("게시글이 없습니다."));
		build.setPostId(post);
		fileRepo.save(build);
		String url = FILE_PATH + REPLAY_PATH;
		url = url + "/" + uuidName + "." + fileExtension;
		Path path = Paths.get(url);
		replay.transferTo(path);
		return true;
	}

	public void saveFile(List<MultipartFile> files, String type, int id) throws Exception {
		for (MultipartFile file : files) {
			String fileExtension = getFileExtension(file.getOriginalFilename());
			String fileNameWithoutExtension = getFileNameWithoutExtension(file.getOriginalFilename());
			String contentType = file.getContentType();
			String uuidName = UUID.randomUUID().toString();
			File build = File.builder()
				.orgName(fileNameWithoutExtension)
				.name(uuidName)
				.extension(fileExtension)
				.type(contentType)
				.isPromise(false)
				.build();
			if (type.equals("post")) {
				Optional<Post> byId = postRepo.findById(id);
				Post post = byId.orElseThrow(() -> new Exception("게시글이 없습니다."));
				build.setPostId(post);
			}
			if (type.equals("goods")) {
				Optional<Goods> byId = goodsRepo.findById(id);
				Goods goods = byId.orElseThrow(() -> new Exception("게시글이 없습니다."));
				build.setGoodsId(goods);
			}
			if (type.equals("event")) {
				Optional<Event> byId = eventRepo.findById(id);
				Event event = byId.orElseThrow(() -> new Exception("게시글이 없습니다."));
				build.setEventId(event);
			}
			fileRepo.save(build);
			String url = FILE_PATH;
			if (contentType.startsWith("image")) {
				url += IMAGE_PATH;
			}
			if (contentType.startsWith("video")) {
				url += VIDEO_PATH;
			}
			url = url + "/" + uuidName + "." + fileExtension;
			Path path = Paths.get(url);
			file.transferTo(path);
		}
	}

	public boolean deleteFile(int fileId) throws Exception {
		log.info("deleteFile fileId: {}", fileId);
		Optional<File> findFileOpt = fileRepo.findById(fileId);
		if (findFileOpt.isEmpty()) {
			throw new Exception("파일이 없습니다.");
		}
		File file = findFileOpt.get();
		String path = FILE_PATH;
		if (file.getType().startsWith("video")) {
			path += VIDEO_PATH;
		}
		if (file.getType().startsWith("image")) {
			path += IMAGE_PATH;
		}
		path = path + file.getName() + "." + file.getExtension();
		fileRepo.deleteById(fileId);
		boolean delete = new java.io.File(path).delete();
		if (!delete) {
			throw new Exception("삭제 실패");
		}
		return true;
	}
}
