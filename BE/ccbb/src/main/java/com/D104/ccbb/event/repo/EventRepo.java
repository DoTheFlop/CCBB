package com.D104.ccbb.event.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.D104.ccbb.event.domain.Event;

public interface EventRepo extends JpaRepository<Event, Integer> {
}
