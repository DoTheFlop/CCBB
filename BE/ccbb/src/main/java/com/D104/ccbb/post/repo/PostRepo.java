package com.D104.ccbb.post.repo;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.D104.ccbb.post.domain.Post;

@Repository
public interface PostRepo extends JpaRepository<Post, Integer> {

	List<Post> findByTypeOrderByPostIdDesc(int type);
	@Query(value = "select p.user_id,p.title,p.content,p.create_date,p.type, v.* from post p join vote v on p.post_id = v.post_id where p.type=1 and v.user1_accept=1 and v.user2_accept=1",nativeQuery = true)
	List<Map<String,Object>> getVoteList();
}
