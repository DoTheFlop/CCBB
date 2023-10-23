package com.D104.ccbb.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.D104.ccbb.user.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
