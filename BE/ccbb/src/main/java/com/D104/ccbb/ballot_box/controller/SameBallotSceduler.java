package com.D104.ccbb.ballot_box.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.D104.ccbb.ballot_box.repo.BallotBoxRepo;
import com.D104.ccbb.payment_history.service.PaymentHistoryService;
import com.D104.ccbb.vote.domain.Vote;
import com.D104.ccbb.vote.repo.VoteRepo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class SameBallotSceduler {

	private final BallotBoxRepo ballotBoxRepo;
	private final VoteRepo voteRepo;
	private final PaymentHistoryService paymentHistoryService;

	// Cron 표현식 사용
	@Scheduled(cron = "0 0 0 * * ?") // 매일 자정에 실행
	public void cronTask() {
		List<Integer> voteIdList = ballotBoxRepo.foundSameVote();
		for (Integer voteId : voteIdList) {
			Optional<Vote> voteOptional = voteRepo.findById(voteId);
			Vote vote = voteOptional.get();
			LocalDateTime deadline = vote.getDeadline();
			if (deadline.toLocalDate().isBefore(LocalDate.now())) {
				String result = paymentHistoryService.returnAllPayment(voteId);
				log.info("result: {}", result);
			}
		}
	}
}
