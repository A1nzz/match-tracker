package com.match_tracker.match_tracker.repository;

import com.match_tracker.match_tracker.entity.Hero;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HeroRepository extends JpaRepository<Hero, Long> {
}
