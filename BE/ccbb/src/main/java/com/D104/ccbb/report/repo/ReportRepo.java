package com.D104.ccbb.report.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.D104.ccbb.report.domain.Report;

@Repository
public interface ReportRepo extends JpaRepository<Report, Integer> {
	List<Report> findByUserId_UserId(int userId);
}
