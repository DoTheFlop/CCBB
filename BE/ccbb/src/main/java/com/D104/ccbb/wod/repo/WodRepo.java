package com.D104.ccbb.wod.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.D104.ccbb.wod.domian.Wod;

public interface WodRepo extends JpaRepository<Wod, Integer> {
}
