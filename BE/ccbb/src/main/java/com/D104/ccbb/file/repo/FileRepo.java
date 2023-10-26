package com.D104.ccbb.file.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.D104.ccbb.file.domain.File;

@Repository
public interface FileRepo extends JpaRepository<File, Integer> {

}
