package com.D104.ccbb.re_comment.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.D104.ccbb.re_comment.domain.ReComment;

@Repository
public interface ReCommentRepo extends JpaRepository<ReComment, Integer> {
	List<ReComment> findByCommentId_CommentId(int commentId);
}
