package com.match_tracker.match_tracker.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class PlayerHero {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "player_id")
    private Player player;

    @ManyToOne
    @JoinColumn(name = "hero_id")
    private Hero hero;

    private Integer gamesPlayed = 1;
}
