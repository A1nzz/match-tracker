package com.match_tracker.match_tracker.repository;

import com.match_tracker.match_tracker.entity.MatchType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchTypeRepository extends JpaRepository<MatchType, Long> {
}
