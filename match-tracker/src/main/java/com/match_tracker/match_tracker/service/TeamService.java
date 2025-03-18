package com.match_tracker.match_tracker.service;

import com.match_tracker.match_tracker.dto.TeamDto;
import com.match_tracker.match_tracker.entity.Player;
import com.match_tracker.match_tracker.entity.Team;
import com.match_tracker.match_tracker.repository.PlayerRepository;
import com.match_tracker.match_tracker.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeamService {

    private final TeamRepository teamRepository;
    private final PlayerRepository playerRepository;

    @Autowired
    public TeamService(TeamRepository teamRepository, PlayerRepository playerRepository) {
        this.teamRepository = teamRepository;
        this.playerRepository = playerRepository;
    }

    public List<TeamDto> getAllTeams() {
        List<Team> teams = teamRepository.findAll();
        return teams.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private TeamDto convertToDTO(Team team) {
        TeamDto dto = new TeamDto();
        dto.setId(team.getId());
        dto.setName(team.getName());
        dto.setRegion(team.getRegion());
        dto.setRating(team.getRating());
        dto.setLogoUrl(team.getLogoUrl());
        List<Player> players = playerRepository.findByTeamId(team.getId());
        dto.setPlayers(players); // Получаем игроков из сущности Team

        return dto;
    }

    public Team getTeamById(Long id) {
        return teamRepository.findById(id).orElse(null);
    }

    public List<Player> getPlayersByTeamId(Long teamId) {
        return playerRepository.findByTeamId(teamId);
    }

    public Team saveTeam(Team team) {
        return teamRepository.save(team);
    }

    public void deleteTeam(Long id) {
        teamRepository.deleteById(id);
    }
}