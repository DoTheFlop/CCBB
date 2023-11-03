package com.D104.ccbb.user.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.D104.ccbb.jwt.service.JwtTokenService;
import com.D104.ccbb.user.domain.User;
import com.D104.ccbb.user.dto.KakaoUserDto;
import com.D104.ccbb.user.dto.UserDto;
import com.D104.ccbb.user.dto.UserEmailPasDto;
import com.D104.ccbb.user.dto.UserLoginDto;
import com.D104.ccbb.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;
	private final JwtTokenService jwtTokenService;
	private final PasswordEncoder passwordEncoder;

	@Value("${spring.security.oauth2.client.provider.kakao.user-info-uri}")
	private String USER_INFO_URI;

	@Value("${spring.security.oauth2.client.registration.kakao.redirect-uri}")
	private String REDIRECT_URI;

	public String getUserInfoFromKakao(String accessToken) throws Exception {
		// RestTemplate 생성
		RestTemplate restTemplate = new RestTemplate();

		// 요청 URL
		String url = USER_INFO_URI;

		// 헤더 설정
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", accessToken);
		headers.set("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

		// HTTP 엔터티 생성 (헤더와 바디 설정)
		HttpEntity<String> entity = new HttpEntity<>(null, headers);

		// GET 요청 보내기
		ResponseEntity<KakaoUserDto> response = restTemplate.exchange(url, HttpMethod.GET, entity, KakaoUserDto.class);

		// 응답 확인
		KakaoUserDto responseBody;
		if (response.getStatusCode().is2xxSuccessful()) {
			responseBody = response.getBody();
			log.info("Response Body: {}", responseBody);
			log.info(responseBody.toString());
		} else {
			log.info("HTTP Request Failed with status code: " + response.getStatusCode());
			throw new Exception("요청이 실패했습니다.");
		}
		Optional<User> findByEmail = userRepository.findByEmail((String)responseBody.getKakao_account().get("email"));
		String createdToken;

		// 조회 안되면 회원가입
		if (findByEmail.isEmpty()) {
			// 데이터 베이스에 유저 저장
			log.info("회원가입 시작");
			User user = User.builder()
				.name(responseBody.getProperties().get("nickname"))
				.nickname(responseBody.getProperties().get("nickname"))
				.email((String)responseBody.getKakao_account().get("email"))
				.password("asdasdasd")
				.sex(true)
				.point(0)
				.build();
			log.info(user.toString());
			User save = userRepository.save(user);
			createdToken = jwtTokenService.createToken(save.getEmail());
			log.info("회원가입 완료");
		} else { // 아니면 로그인
			log.info("로그인 시작");
			createdToken = jwtTokenService.createToken(findByEmail.get().getEmail());
			log.info("로그인 완료");
		}

		return createdToken;
	}

	@Transactional
	public void eSignup(UserLoginDto userLoginDto) {
		if (userRepository.findByEmail(userLoginDto.getEmail()).isPresent()) {
			throw new IllegalArgumentException("이미 존재하는 이메일입니다.");
		}
		String encodedPassword = passwordEncoder.encode(userLoginDto.getPassword());

		User user = User.builder()
			.nickname(userLoginDto.getNickname())
			.name(userLoginDto.getName())
			.email(userLoginDto.getEmail())
			.password(encodedPassword)
			.sex(userLoginDto.getSex())
			.point(10000)
			.createDate(LocalDateTime.now())
			.state((byte)1)
			.voteCount(0)
			.voteVictory(0)
			.build();
		userRepository.save(user);
	}

	public String elogin(UserEmailPasDto userEmailPasDto) {
		User user = userRepository.findByEmail(userEmailPasDto.getEmail())
			.orElseThrow(() -> new IllegalArgumentException("해당 이메일의 유저가 존재하지 않습니다."));

		if (!passwordEncoder.matches(userEmailPasDto.getPassword(), user.getPassword())) {
			throw new IllegalArgumentException("비밀번호가 틀렸습니다.");
		}

		// 토큰 생성 및 반환
		return jwtTokenService.createToken(user.getEmail());
	}

	@Transactional
	public void updateUser(String email, UserDto userDto) {
		User user = userRepository.findByEmail(email)
			.orElseThrow(() -> new IllegalStateException(" No User"));
		user.setNickname(userDto.getNickname());
		user.setSex(userDto.getSex());
		userRepository.save(user); // 안적어도 @Transactional 때문에 저장이 자동으로 됨 . 가독성 때매 놔둔거임
	}

	@Transactional
	public void updateVote(Integer userId, Integer a) {
		User user = userRepository.getReferenceById(userId);
		user.setVoteCount(user.getVoteCount() + 1);
		user.setVoteVictory(user.getVoteVictory() + a);
		userRepository.save(user);
	}

	public UserDto getUserProfile(String email) {
		User user = userRepository.findByEmail(email)
			.orElseThrow(() -> new IllegalStateException(" No User"));

		return UserDto.fromEntity(user);
	}

	public void deleteUser(String email) {
		User user = userRepository.findByEmail(email)
			.orElseThrow(() -> new IllegalStateException("No user found with the provided email"));

		userRepository.delete(user);
	}

	public boolean userEmailCheck(String userEmail, String userName) {

		// User user = userRepository.findUserByUserId(userEmail).get();
		// if(user!=null && user.getName().equals(userName)) {
		// 	return true;
		// }
		// else {
		// 	return false;
		// }

		User user = userRepository.findByEmail(userEmail).get();
		if (user != null && user.getName().equals(userName)) {
			return true;
		} else {
			return false;
		}
	}

	public boolean findUser(String email) {
		Optional<User> findUserOpt = userRepository.findByEmail(email);
		if (findUserOpt.isEmpty()) {
			throw new UsernameNotFoundException("존재하지 않는 유저입니다.");
		}
		return true;
	}
}
