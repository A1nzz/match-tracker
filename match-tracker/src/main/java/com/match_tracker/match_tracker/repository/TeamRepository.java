package com.match_tracker.match_tracker.repository;

import com.match_tracker.match_tracker.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long> {
}