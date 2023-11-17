package com.D104.ccbb.profile_img.service;

import java.io.File;
import java.util.Optional;
import java.util.UUID;

import org.aspectj.util.FileUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.D104.ccbb.jwt.service.JwtTokenService;
import com.D104.ccbb.profile_img.domain.ProfileImg;
import com.D104.ccbb.profile_img.dto.ProfileImgDto;
import com.D104.ccbb.profile_img.repo.ProfileImgRepo;
import com.D104.ccbb.user.domain.User;
import com.D104.ccbb.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProfileImgService {
	private final ProfileImgRepo profileImgRepo;
	private final JwtTokenService jwtTokenService;
	private final UserRepository userRepository;

	@Value("${spring.servlet.multipart.location}")
	private String FILE_PATH;

	@Value("${file.profileImg}")
	private String PROFILE_IMG;

	public byte[] getProfileImg(int userId) throws Exception {
		Optional<User> userOpt = userRepository.findById(userId);
		if (userOpt.isEmpty()) {
			throw new Exception("없는 유저입니다.");
		}
		ProfileImg userProfileImg = profileImgRepo.findByUserId_UserId(userId);

		String file_path = FILE_PATH + PROFILE_IMG + "/" + userProfileImg.getName();
		File file = new File(file_path);
		return FileUtil.readAsByteArray(file);
	}

	@Transactional
	public ProfileImg modifyImg(String authorization, MultipartFile file) throws
		Exception {
		// 프로필 등록하는 유저 확인
		String userEmail = jwtTokenService.getUserEmail(jwtTokenService.extractToken(authorization));
		Optional<User> byEmail = userRepository.findByEmail(userEmail);
		if (byEmail.isEmpty()) {
			throw new Exception("유저를 찾을 수 없습니다.");
		}

		// DB에 저장된 프로필 정보 가져오기
		// 만약 조회되지 않으면 신규로, 조회되면 수정으로 넘어감
		ProfileImg byUserIdUserId = profileImgRepo.findByUserId_UserId(byEmail.get().getUserId());
		if (byUserIdUserId == null) {
			// 신규 등록
			log.info("신규 등록");
			// 받은 파일의 이름을 확장자와 원본 이름으로 분리
			String fileName = getFileNameWithoutExtension(file.getOriginalFilename());
			String fileExtension = getFileExtension(file.getOriginalFilename());
			// 랜덤 이름 생성
			String uuidName = UUID.randomUUID().toString();
			// 파일 경로에 파일을 저장
			String profile_path = FILE_PATH + PROFILE_IMG + "/" + uuidName;
			File dest = new File(profile_path);
			file.transferTo(dest);
			// 저장한 파일 정보를 DB에 저장
			ProfileImg build = ProfileImg.builder()
				.name(uuidName)
				.orgName(fileName)
				.extension(fileExtension)
				.userId(byEmail.get())
				.build();
			ProfileImg save = profileImgRepo.save(build);
			return save;
		}
		log.info("이미지 수정");
		String profileBasePath = FILE_PATH + PROFILE_IMG + "/";
		// 기존 파일 정보를 활용해서 서버 DB에서 파일을 삭제
		String profile_path = profileBasePath + byUserIdUserId.getName();
		File fileImg = new File(profile_path);
		fileImg.delete();
		// 수정할 이미지로 정보 수정
		String uuidName = UUID.randomUUID().toString();
		String modifyPath = profileBasePath + uuidName;
		file.transferTo(new File(modifyPath));
		byUserIdUserId.setName(uuidName);
		byUserIdUserId.setOrgName(file.getOriginalFilename());
		profileImgRepo.save(byUserIdUserId);
		return byUserIdUserId;
	}

	@Transactional
	public String deleteImg(String authorization, int profileimgId) throws Exception {
		// 유저 조회
		String userEmail = jwtTokenService.getUserEmail(jwtTokenService.extractToken(authorization));
		Optional<User> byEmail = userRepository.findByEmail(userEmail);
		if (byEmail.isEmpty()) {
			throw new Exception("유저를 찾을 수 없습니다.");
		}
		// 유저의 프로필 이미지 정보 조회
		Optional<ProfileImg> userProfileImgOpt = profileImgRepo.findById(profileimgId);
		if (userProfileImgOpt.isEmpty()) {
			throw new Exception("이미지를 찾을 수 없습니다.");
		}
		// 삭제하고자 하는 이미지가 본인의 프로필인지 확인
		if (!userProfileImgOpt.get().getUserId().getUserId().equals(byEmail.get().getUserId())) {
			throw new Exception("본인의 프로필만 바꿀 수 있습니다.");
		}
		// 이미지도 있고, 유저도 있고, 본인의 이미지인지 확인 했으니 이미지 삭제
		String file_path = FILE_PATH + PROFILE_IMG + "/" + userProfileImgOpt.get().getName();
		File file = new File(file_path);
		log.info(file.getName());
		boolean delete = file.delete();
		log.info(delete ? "완료" : "실패");
		profileImgRepo.deleteById(profileimgId);
		return "삭제완료";
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

	public ProfileImgDto getProfileImgMetaData(String authorization) throws Exception {
		String userEmail = jwtTokenService.getUserEmail(jwtTokenService.extractToken(authorization));
		Optional<User> byEmail = userRepository.findByEmail(userEmail);
		if (byEmail.isEmpty()) {
			throw new Exception("존재하지 않는 유저입니다");
		}
		ProfileImg byUserIdUserId = profileImgRepo.findByUserId_UserId(byEmail.get().getUserId());
		return ProfileImgDto.builder()
			.profileImgId(byUserIdUserId.getProfileImgId())
			.orgName(byUserIdUserId.getOrgName())
			.name(byUserIdUserId.getName())
			.userId(byUserIdUserId.getUserId().getUserId())
			.extension(byUserIdUserId.getExtension())
			.build();
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
