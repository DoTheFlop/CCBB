package com.D104.ccbb.report.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.D104.ccbb.report.domain.Report;

public interface ReportRepo extends JpaRepository<Report, Integer> {
}
