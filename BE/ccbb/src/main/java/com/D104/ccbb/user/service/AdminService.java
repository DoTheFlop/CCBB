package com.D104.ccbb.user.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.D104.ccbb.post.domain.Post;
import com.D104.ccbb.user.domain.User;
import com.D104.ccbb.user.dto.UserDto;
import com.D104.ccbb.user.repository.UserRepository;
import com.nimbusds.oauth2.sdk.util.ListUtils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class AdminService {
	private final UserRepository userRepository;
	public List<User> getUser() {
		log.info("service 호출");
		log.info("list: {}", userRepository.findAllByUserId(3));
		log.info("service 완료");
		return userRepository.findAll();
	}

	@Transactional
	public void updateSuspend(Integer id) {
		// User user = userRepository.findById(id).orElseThrow(() -> new IllegalStateException("존재하지 않는 유저입니다."));
		User user = userRepository.getReferenceById(id);
		user.setState((byte)2);
		userRepository.save(user);
	}

	@Transactional
	public void updateUnSuspend(Integer id) {
		// User user = userRepository.findById(id).orElseThrow(() -> new IllegalStateException("존재하지 않는 유저입니다."));
		User user = userRepository.getReferenceById(id);
		user.setState((byte)1);
		userRepository.save(user);
	}
}
