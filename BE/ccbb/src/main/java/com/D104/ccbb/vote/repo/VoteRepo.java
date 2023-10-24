package com.D104.ccbb.vote.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.D104.ccbb.vote.domain.Vote;

@Repository
public interface VoteRepo extends JpaRepository<Vote, Integer> {
}
