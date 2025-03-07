package com.match_tracker.match_tracker.repository;


import com.match_tracker.match_tracker.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Long> {
}
