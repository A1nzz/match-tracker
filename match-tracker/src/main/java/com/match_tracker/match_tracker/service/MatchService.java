package com.match_tracker.match_tracker.service;

import com.match_tracker.match_tracker.dto.GameDto;
import com.match_tracker.match_tracker.entity.Game;
import com.match_tracker.match_tracker.entity.GameWithScores;
import com.match_tracker.match_tracker.entity.Match;
import com.match_tracker.match_tracker.repository.GameRepository;
import com.match_tracker.match_tracker.repository.GameWithScoresRepository;
import com.match_tracker.match_tracker.repository.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MatchService {

    private final MatchRepository matchRepository;
    private final GameWithScoresService gameWithScoresService;

    @Autowired
    public MatchService(MatchRepository matchRepository, GameWithScoresService gameWithScoresService) {
        this.matchRepository = matchRepository;
        this.gameWithScoresService = gameWithScoresService;
    }

    public List<Match> getAllMatches() {
        return matchRepository.findAll();
    }

    public Match getMatchById(Long id) {
        return matchRepository.findById(id).orElse(null);
    }

    public List<GameDto> getGamesByMatchId(Long matchId) {
        return gameWithScoresService.getGamesByMatchId(matchId);
    }

    public Match saveMatch(Match match) {
        return matchRepository.save(match);
    }

    public void deleteMatch(Long id) {
        matchRepository.deleteById(id);
    }
}