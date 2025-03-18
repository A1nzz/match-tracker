package com.match_tracker.match_tracker.service;

import com.match_tracker.match_tracker.dto.MatchDto;
import com.match_tracker.match_tracker.entity.*;
import com.match_tracker.match_tracker.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.match_tracker.match_tracker.dto.GameDto;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MatchWithScoresService {

    private final MatchWithScoresRepository matchWithScoresRepository;
    private final TournamentRepository tournamentRepository;
    private final MatchTypeRepository matchTypeRepository;
    private final TeamRepository teamRepository;

    @Autowired
    public MatchWithScoresService(MatchWithScoresRepository matchWithScoresRepository, TournamentRepository tournamentRepository, MatchTypeRepository matchTypeRepository, TeamRepository teamRepository) {
        this.matchWithScoresRepository = matchWithScoresRepository;
        this.tournamentRepository = tournamentRepository;
        this.matchTypeRepository = matchTypeRepository;
        this.teamRepository = teamRepository;
    }

    public List<MatchDto> getAllMatches() {
        List<MatchWithScores> matches = matchWithScoresRepository.findAll();
        return matches.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public List<MatchDto> getMatchesByTournamentId(Long tournamentId) {
        List<MatchWithScores> games = matchWithScoresRepository.findByTournamentId(tournamentId);
        return games.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private MatchDto convertToDTO(MatchWithScores matchWithScores) {
        MatchDto dto = new MatchDto();
        dto.setId(matchWithScores.getMatchId());
        dto.setTournament(tournamentRepository.findById(matchWithScores.getTournamentId()).orElse(null));
        dto.setRadiantScore(matchWithScores.getRadiantScore());
        dto.setDireScore(matchWithScores.getDireScore());
        dto.setBestOf(matchWithScores.getBestOf());
        dto.setMatchType(matchTypeRepository.findById(matchWithScores.getMatchTypeId()).orElse(null));
        dto.setTeamRadiant(teamRepository.findById(matchWithScores.getTeamRadiantId()).orElse(null));
        dto.setTeamDire(teamRepository.findById(matchWithScores.getTeamDireId()).orElse(null));
        dto.setMatchDate(matchWithScores.getMatchDate());
        return dto;
    }
}
