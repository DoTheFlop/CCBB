package com.D104.ccbb.user.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.D104.ccbb.user.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	List<User> findAllByUserId(int userId);
	Optional<User> findByEmail(String email);

	// 메소드 이름 변경
	Optional<User> findUserByEmail(String email);

	@Modifying
	@Transactional
	@Query("UPDATE User u SET u.password = :password WHERE u.id = :id")
	void updateUserPassword(@Param("id") int id, @Param("password") String password);
}
