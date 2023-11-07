package com.D104.ccbb.comment.repo;

import com.D104.ccbb.comment.domain.Comment;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepo extends JpaRepository<Comment, Integer> {
    @EntityGraph(attributePaths = {"reComment", "userId"})
    List<Comment> findByPostId_PostId(int postId);

    Long countByPostId_PostId(int postId);
}
