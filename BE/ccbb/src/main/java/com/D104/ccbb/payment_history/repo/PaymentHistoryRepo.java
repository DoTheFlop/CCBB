package com.D104.ccbb.payment_history.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.D104.ccbb.payment_history.domain.PaymentHistory;

@Repository
public interface PaymentHistoryRepo extends JpaRepository<PaymentHistory, Integer> {

	PaymentHistory findByUserId_UserIdAndVoteId_VoteId(int userId, int voteId);
	
}
