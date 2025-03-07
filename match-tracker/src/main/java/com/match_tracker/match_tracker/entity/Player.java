package com.match_tracker.match_tracker.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nickname;

    private String realName;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    private String role;

    private String country;
}