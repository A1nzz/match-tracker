package com.match_tracker.match_tracker.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class GameStats {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;

    @ManyToOne
    @JoinColumn(name = "player_hero_id")
    private PlayerHero playerHero;

    private Integer kills = 0;

    private Integer deaths = 0;

    private Integer assists = 0;

    private Integer lastHits = 0;

    private Integer goldPerMinute = 0;

    private Integer xpPerMinute = 0;

    private Double netWorth = 0.0;

    private Integer finalLevel = 1;
}
