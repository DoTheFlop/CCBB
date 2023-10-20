package com.D104.ccbb.participants.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.D104.ccbb.participants.domain.Participants;

public interface ParticipantsRepo extends JpaRepository<Participants, Integer> {
}
