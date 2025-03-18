package com.match_tracker.match_tracker.repository;

import com.match_tracker.match_tracker.entity.Match;
import com.match_tracker.match_tracker.entity.MatchWithScores;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MatchWithScoresRepository extends JpaRepository<MatchWithScores, Long> {
    List<MatchWithScores> findByTournamentId(Long tournamentId);
}