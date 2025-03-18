package com.match_tracker.match_tracker.repository;

import com.match_tracker.match_tracker.entity.GameWithScores;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GameWithScoresRepository extends JpaRepository<GameWithScores, Long> {
    List<GameWithScores> findByMatchId(Long matchId);
}
