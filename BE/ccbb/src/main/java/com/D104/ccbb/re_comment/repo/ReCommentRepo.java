package com.D104.ccbb.re_comment.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.D104.ccbb.re_comment.domain.ReComment;

public interface ReCommentRepo extends JpaRepository<ReComment, Integer> {
}
