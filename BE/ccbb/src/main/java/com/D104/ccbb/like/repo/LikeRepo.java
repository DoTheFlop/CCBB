package com.D104.ccbb.like.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.D104.ccbb.like.domain.Like;

public interface LikeRepo extends JpaRepository<Like, Integer> {
}
