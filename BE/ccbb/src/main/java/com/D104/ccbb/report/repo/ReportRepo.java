package com.D104.ccbb.report.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.D104.ccbb.report.domain.Report;

@Repository
public interface ReportRepo extends JpaRepository<Report, Integer> {
}
