package com.match_tracker.match_tracker.repository;

import com.match_tracker.match_tracker.entity.Match;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchRepository extends JpaRepository<Match, Long> {
}
