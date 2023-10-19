package com.D104.ccbb.vote.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.D104.ccbb.vote.domain.Vote;

public interface VoteRepo extends JpaRepository<Vote, Integer> {
}
