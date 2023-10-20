package com.D104.ccbb.comment.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.D104.ccbb.comment.domain.Comment;

public interface CommentRepo extends JpaRepository<Comment, Integer> {
}
