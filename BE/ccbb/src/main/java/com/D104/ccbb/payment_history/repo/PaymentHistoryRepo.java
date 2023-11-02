package com.D104.ccbb.payment_history.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.D104.ccbb.payment_history.domain.PaymentHistory;
import com.D104.ccbb.user.domain.User;

@Repository
public interface PaymentHistoryRepo extends JpaRepository<PaymentHistory, Integer> {

	PaymentHistory findByUserId_UserIdAndVoteId_VoteId(int userId, int voteId);

	PaymentHistory findByUserId_UserIdIsNotAndVoteId_VoteId(int userId, int voteId);

	List<PaymentHistory> findByUserId(User user);

	List<PaymentHistory> findAllByVoteId_VoteId(int voteId);

}
