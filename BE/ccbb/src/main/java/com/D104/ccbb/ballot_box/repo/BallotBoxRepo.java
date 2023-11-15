package com.D104.ccbb.ballot_box.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.D104.ccbb.ballot_box.domain.BallotBox;

@Repository
public interface BallotBoxRepo extends JpaRepository<BallotBox, Integer> {

	Long countByVote_VoteIdAndPick(int voteId, int pick);
	Long countByVote_VoteId(int voteId);
	List<BallotBox> findByVote_VoteId(int voteId);
	BallotBox findByVote_VoteIdAndUserId_UserId(int voteId, int userId);


	@Query(value = "SELECT vote_id\n"
		+ "FROM (\n"
		+ "    SELECT \n"
		+ "        COUNT(CASE WHEN pick = 1 THEN 1 END) AS one,\n"
		+ "        COUNT(CASE WHEN pick = 2 THEN 1 END) AS two,\n"
		+ "        vote_id\n"
		+ "    FROM ballot_box\n"
		+ "    GROUP BY vote_id\n"
		+ ") AS subquery\n"
		+ "WHERE one = two;", nativeQuery = true)
	List<Integer> foundSameVote();
}
