package com.D104.ccbb.user.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.D104.ccbb.user.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	List<User> findAllByUserId(int userId);
	Optional<User> findByEmail(String email);
}
