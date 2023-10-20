package com.D104.ccbb.ballot_box.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.D104.ccbb.ballot_box.domain.BallotBox;

public interface BalloBoxRepo extends JpaRepository<BallotBox, Integer> {
}
