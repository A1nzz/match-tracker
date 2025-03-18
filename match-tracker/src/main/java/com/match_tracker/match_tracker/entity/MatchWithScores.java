package com.match_tracker.match_tracker.entity;

import jakarta.persistence.*;
import lombok.Data;
import com.match_tracker.match_tracker.entity.Tournament;

import java.sql.Date;


@Entity
@Data
@Table(name = "match_with_wins")
public class MatchWithScores {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long matchId;

    private Integer radiantScore;

    private Integer direScore;

    private Long tournamentId;

    private Integer bestOf;

    private Date matchDate;

    private Long matchTypeId;

    private Long teamRadiantId;

    private Long teamDireId;
}

