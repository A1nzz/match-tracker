package com.match_tracker.match_tracker.controller;

import com.match_tracker.match_tracker.entity.Player;
import com.match_tracker.match_tracker.entity.Team;
import com.match_tracker.match_tracker.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teams")
public class TeamController {

    @Autowired
    private TeamService teamService;

    @GetMapping
    public List<Team> getAllTeams() {
        return teamService.getAllTeams();
    }

    @GetMapping("/{id}")
    public Team getTeamById(@PathVariable Long id) {
        return teamService.getTeamById(id);
    }

    @GetMapping("/{teamId}/players")
    public List<Player> getPlayersByTeamId(@PathVariable Long teamId) {
        return teamService.getPlayersByTeamId(teamId);
    }
}