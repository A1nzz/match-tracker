package com.match_tracker.match_tracker.controller;

import com.match_tracker.match_tracker.dto.TeamDto;
import com.match_tracker.match_tracker.entity.Player;
import com.match_tracker.match_tracker.entity.Team;
import com.match_tracker.match_tracker.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teams")
public class TeamController {

    private final TeamService teamService;

    @Autowired
    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping
    public List<TeamDto> getAllTeams() {
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