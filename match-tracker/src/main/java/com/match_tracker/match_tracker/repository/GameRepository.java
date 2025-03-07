package com.match_tracker.match_tracker.repository;

import com.match_tracker.match_tracker.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Long> {
}