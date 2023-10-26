package com.D104.ccbb.ballot_box.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.D104.ccbb.ballot_box.domain.BallotBox;

public interface BallotBoxRepo extends JpaRepository<BallotBox, Integer> {
}
