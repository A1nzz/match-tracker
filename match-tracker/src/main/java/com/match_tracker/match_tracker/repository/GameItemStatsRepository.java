package com.match_tracker.match_tracker.repository;

import com.match_tracker.match_tracker.entity.GameItemStats;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameItemStatsRepository extends JpaRepository<GameItemStats, Long> {
}
