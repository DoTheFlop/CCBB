package com.D104.ccbb.comment.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.D104.ccbb.comment.domain.Comment;

@Repository
public interface CommentRepo extends JpaRepository<Comment, Integer> {
	List<Comment> findByPostId_PostId(int postId);
}
