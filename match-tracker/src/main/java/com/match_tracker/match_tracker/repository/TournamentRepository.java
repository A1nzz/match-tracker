package com.match_tracker.match_tracker.repository;

import com.match_tracker.match_tracker.entity.Tournament;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TournamentRepository extends JpaRepository<Tournament, Long> {
}