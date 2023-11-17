package com.D104.ccbb.like.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.D104.ccbb.like.domain.Likes;

@Repository
public interface LikesRepo extends JpaRepository<Likes, Integer> {
}
