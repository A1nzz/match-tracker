package com.match_tracker.match_tracker.repository;

import com.match_tracker.match_tracker.entity.PlayerHero;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerHeroRepository extends JpaRepository<PlayerHero, Long> {
}
