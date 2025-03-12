package com.match_tracker.match_tracker.repository;

import com.match_tracker.match_tracker.entity.GameStats;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GameStatsRepository extends JpaRepository<GameStats, Long> {
    List<GameStats> findByGameId(Long gameId);
}
