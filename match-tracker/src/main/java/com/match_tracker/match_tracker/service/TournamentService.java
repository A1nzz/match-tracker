package com.match_tracker.match_tracker.service;

import com.match_tracker.match_tracker.dto.MatchDto;
import com.match_tracker.match_tracker.entity.Tournament;
import com.match_tracker.match_tracker.repository.TournamentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TournamentService {

    private final TournamentRepository tournamentRepository;
    private final MatchWithScoresService matchWithScoresService;

    @Autowired
    public TournamentService(TournamentRepository tournamentRepository, MatchWithScoresService matchWithScoresService) {
        this.tournamentRepository = tournamentRepository;

        this.matchWithScoresService = matchWithScoresService;
    }

    public List<Tournament> getAllTournaments() {
        return tournamentRepository.findAll();
    }

    public Tournament getTournamentById(Long id) {
        return tournamentRepository.findById(id).orElse(null);
    }

    public Tournament saveTournament(Tournament tournament) {
        return tournamentRepository.save(tournament);
    }

    public void deleteTournament(Long id) {
        tournamentRepository.deleteById(id);
    }

    public List<MatchDto> getMatchesByTournamentId(Long tournamentId) {
        return matchWithScoresService.getMatchesByTournamentId(tournamentId);
    }
}