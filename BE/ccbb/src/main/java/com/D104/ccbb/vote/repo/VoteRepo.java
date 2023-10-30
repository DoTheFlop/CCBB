package com.D104.ccbb.vote.repo;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.D104.ccbb.vote.domain.Vote;

@Repository
public interface VoteRepo extends JpaRepository<Vote, Integer> {

	List<Vote> findByDeadLineLessThan(LocalDateTime deadLine);
}
