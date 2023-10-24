package com.D104.ccbb.ballot_box.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.D104.ccbb.ballot_box.domain.BallotBox;

@Repository
public interface BallotBoxRepo extends JpaRepository<BallotBox, Integer> {
}
