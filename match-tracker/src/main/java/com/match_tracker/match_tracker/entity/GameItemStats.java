package com.match_tracker.match_tracker.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class GameItemStats {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "game_stats_id")
    private GameStats gameStats;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    private Integer quantity = 1;
}
