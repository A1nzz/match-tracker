package com.match_tracker.match_tracker.dto;

import com.match_tracker.match_tracker.entity.Tournament;
import com.match_tracker.match_tracker.entity.Team;
import com.match_tracker.match_tracker.entity.Hero;
import lombok.Data;

import java.util.List;

@Data
public class HomePageDto {
    private List<Tournament> tournaments;
    private List<Team> teams;
    private List<Hero> heroes;
}