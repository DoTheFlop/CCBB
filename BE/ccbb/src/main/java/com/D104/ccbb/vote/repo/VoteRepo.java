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

import com.D104.ccbb.post.domain.Post;
import com.D104.ccbb.vote.domain.Vote;

@Repository
public interface VoteRepo extends JpaRepository<Vote, Integer> {

	List<Vote> findByDeadlineLessThan(LocalDateTime deadLine);

	Optional<Vote> findByPostId_PostId(int postId);

	@Query(value = "select * from vote where (user1_accept=?1 or user2_accept=?2) and vote_deadline>now();", nativeQuery = true)
	List<Vote> participateList(int userid1, int userid2);
	@Query(value = "select * from vote where (user1_accept=?1 or user2_accept=?2) and vote_deadline<now();", nativeQuery = true)
	List<Vote> participatePastList(int userid1, int userid2);
	List<Vote> findByUserId2_UserIdAndAccept2(int userId, Boolean a);

	@Query(value = "select v.*, count(b.vote_id) popular\n"
		+ "from  ballot_box b right join vote v on b.vote_id = v.vote_id \n"
		+ "where v.user1_accept = true and v.user2_accept = true\n"
		+ "group by v.vote_id order by popular desc", countQuery = "select count(*) from ballot_box b join vote v on b.vote_id = v.vote_id where v.user1_accept = 1 and v.user2_accept = 1" ,nativeQuery = true)
	Page<Map<String, Object>> popularPage(PageRequest pageRequest);
	@Query(value = "select v.*, count(b.vote_id) popular\n"
		+ "from  ballot_box b right join vote v on b.vote_id = v.vote_id\n"
		+ "where v.user1_accept = true and v.user2_accept = true and v.vote_deadline < now()\n"
		+ "group by v.vote_id order by popular desc", countQuery = "select count(*) from ballot_box b join vote v on b.vote_id = v.vote_id where v.user1_accept = 1 and v.user2_accept = 1 and v.vote_deadline < now()" ,nativeQuery = true)
	Page<Map<String, Object>> popularPastPage(PageRequest pageRequest);

	@Query(value = "select v.* from vote v join post p where v.vote_deadline < now() and v.user1_accept = true and v.user2_accept = true group by vote_id", nativeQuery = true)
	Page<Vote> pastList(PageRequest pageRequest);


}
