package com.D104.ccbb.point_history.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.D104.ccbb.point_history.domain.PointHistory;

@Repository
public interface PointHistoryRepo extends JpaRepository<PointHistory, Integer> {
	List<PointHistory> findByUserId_UserId(int userId);
}
