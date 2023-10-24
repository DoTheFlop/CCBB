package com.D104.ccbb.profile_img.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.D104.ccbb.profile_img.repo.ProfileImgRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProfileImgService {
	private final ProfileImgRepo profileImgRepo;

	public void addImg(String authorization, MultipartFile file) {
		String fileName = getFileNameWithoutExtension(file.getOriginalFilename());
		String fileExtension = getFileExtension(file.getOriginalFilename());
	}

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

	/*
	public FanDto writeProfileImage(MultipartFile file, String email) throws
		NoSuchAlgorithmException,
		IOException {
		Optional<FanDto> userDto = fanRepository.findByEmail(email);
		System.out.println("=============");
		String fileName = getFileNameWithoutExtension(file.getOriginalFilename());
		String fileExtension = getFileExtension(file.getOriginalFilename());
		String hashName = generateUniqueFileName(fileName);
		String profile_path = FILE_PATH + PROFILE_FOLDER + hashName;
		System.out.println(profile_path);
		File dest = new File(profile_path);
		file.transferTo(dest);

		ProfileFileDto profileFileDto = ProfileFileDto.builder()
			.filePath(profile_path)
			.fileExtension(fileExtension).build();
		ProfileFileDto save = profileFileRepository.save(profileFileDto);
		FanDto fanDto = userDto.get();
		fanDto.setProfileFileDto(save);
		fanRepository.save(fanDto);

		return fanDto;
	}

	 */
}
