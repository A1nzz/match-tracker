package com.match_tracker.match_tracker.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;


@Entity
@Data
@Table(name = "game_with_scores")
public class GameWithScores {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long gameId;

    private Integer duration;

    private Long matchId;

    private String winner;

    private LocalDateTime startTime;

    private Integer radiantScore;

    private Integer direScore;
}
