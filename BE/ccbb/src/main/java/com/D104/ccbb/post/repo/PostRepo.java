package com.D104.ccbb.post.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.D104.ccbb.post.domain.Post;

public interface PostRepo extends JpaRepository<Post, Integer> {
}
