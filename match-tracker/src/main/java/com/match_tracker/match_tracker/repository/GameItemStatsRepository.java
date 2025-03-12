package com.match_tracker.match_tracker.repository;

import com.match_tracker.match_tracker.entity.GameItemStats;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GameItemStatsRepository extends JpaRepository<GameItemStats, Long> {
    List<GameItemStats> findByGameStatsId(Long gameStatsId);
    void deleteByGameStatsId(Long gameStatsId);
}
