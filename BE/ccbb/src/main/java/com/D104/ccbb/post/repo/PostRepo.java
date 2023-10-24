package com.D104.ccbb.post.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.D104.ccbb.post.domain.Post;

@Repository
public interface PostRepo extends JpaRepository<Post, Integer> {

}
