package com.match_tracker.match_tracker.repository;

import com.match_tracker.match_tracker.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
