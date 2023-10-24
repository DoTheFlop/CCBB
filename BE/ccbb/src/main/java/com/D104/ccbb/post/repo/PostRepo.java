package com.D104.ccbb.post.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.D104.ccbb.post.domain.Post;

@Repository
public interface PostRepo extends JpaRepository<Post, Integer> {

	List<Post> findByTypeOrderByPostIdDesc(int type);
}
