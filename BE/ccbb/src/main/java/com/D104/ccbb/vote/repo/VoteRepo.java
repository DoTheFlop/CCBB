package com.D104.ccbb.vote.repo;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.D104.ccbb.vote.domain.Vote;

@Repository
public interface VoteRepo extends JpaRepository<Vote, Integer> {

	List<Vote> findByDeadlineLessThan(LocalDateTime deadLine);

	Optional<Vote> findByPostId_PostId(int postId);

	List<Vote> findByUserId1_UserIdOrUserId2_UserId(int userId1, int userId2);
	List<Vote> findByUserId2_UserIdAndAccept2(int userId, Boolean a);
}
