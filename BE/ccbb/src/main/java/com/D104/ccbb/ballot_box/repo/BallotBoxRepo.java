package com.D104.ccbb.ballot_box.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.D104.ccbb.ballot_box.domain.BallotBox;

@Repository
public interface BallotBoxRepo extends JpaRepository<BallotBox, Integer> {

	Long countByVote_VoteIdAndPick(int voteId, int pick);
	List<BallotBox> findByVote_VoteId(int voteId);
	BallotBox findByVote_VoteIdAndUserId_UserId(int voteId, int userId);
}
