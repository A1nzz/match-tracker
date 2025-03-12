package com.match_tracker.match_tracker.repository;

import com.match_tracker.match_tracker.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GameRepository extends JpaRepository<Game, Long> {
    List<Game> findByMatchId(Long matchId);
}