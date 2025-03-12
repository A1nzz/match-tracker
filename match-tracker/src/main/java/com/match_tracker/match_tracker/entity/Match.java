package com.match_tracker.match_tracker.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "tournament_id")
    private Tournament tournament;

    @ManyToOne
    @JoinColumn(name = "team_radiant_id")
    private Team teamRadiant;

    @ManyToOne
    @JoinColumn(name = "team_dire_id")
    private Team teamDire;

    @ManyToOne
    @JoinColumn(name = "match_type_id")
    private MatchType matchType;

    private Integer bestOf;

    @Temporal(TemporalType.DATE)
    private Date matchDate;
}