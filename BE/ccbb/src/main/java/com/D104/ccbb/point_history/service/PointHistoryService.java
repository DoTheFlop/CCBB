package com.D104.ccbb.point_history.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.D104.ccbb.jwt.service.JwtTokenService;
import com.D104.ccbb.point_history.domain.PointHistory;
import com.D104.ccbb.point_history.dto.PointHistoryDto;
import com.D104.ccbb.point_history.repo.PointHistoryRepo;
import com.D104.ccbb.user.domain.User;
import com.D104.ccbb.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class PointHistoryService {

	private final JwtTokenService jwtTokenService;
	private final UserRepository userRepository;
	private final PointHistoryRepo pointHistoryRepo;

	public List<PointHistoryDto> getPointHistory(String authorization) throws Exception {
		String userEmail = jwtTokenService.getUserEmail(jwtTokenService.extractToken(authorization));
		Optional<User> foundUserOpt = userRepository.findByEmail(userEmail);
		if (foundUserOpt.isEmpty()) {
			throw new Exception("존재하지 않는 유저입니다.");
		}
		List<PointHistory> pointHistoryList = pointHistoryRepo.findByUserId_UserId(foundUserOpt.get().getUserId());

		return pointHistoryList.stream()
			.map(PointHistoryDto::fromEntity)
			.collect(Collectors.toList());
	}

}
