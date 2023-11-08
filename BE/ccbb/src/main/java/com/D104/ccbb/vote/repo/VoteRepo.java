package com.D104.ccbb.vote.repo;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.D104.ccbb.vote.domain.Vote;

@Repository
public interface VoteRepo extends JpaRepository<Vote, Integer> {

	List<Vote> findByDeadlineLessThan(LocalDateTime deadLine);

	Optional<Vote> findByPostId_PostId(int postId);

	List<Vote> findByUserId1_UserIdOrUserId2_UserId(int userId1, int userId2);
	List<Vote> findByUserId2_UserIdAndAccept2(int userId, Boolean a);

	@Query(value = "select v.*, count(*) popular\n"
		+ "from  ballot_box b join vote v on b.vote_id = v.vote_id \n"
		+ "where v.user1_accept = true and v.user2_accept = true\n"
		+ "group by v.vote_id order by popular desc", countQuery = "select count(*) from ballot_box b join vote v on b.vote_id = v.vote_id where v.user1_accept = 1 and v.user2_accept = 1" ,nativeQuery = true)
	Page<Map<String, Object>> popularPage(PageRequest pageRequest);
}
