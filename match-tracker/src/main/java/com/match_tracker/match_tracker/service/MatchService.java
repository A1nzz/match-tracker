package com.match_tracker.match_tracker.service;

import com.match_tracker.match_tracker.entity.Game;
import com.match_tracker.match_tracker.entity.Match;
import com.match_tracker.match_tracker.repository.GameRepository;
import com.match_tracker.match_tracker.repository.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatchService {

    @Autowired
    private MatchRepository matchRepository;

    @Autowired
    private GameRepository gameRepository;

    public List<Match> getAllMatches() {
        return matchRepository.findAll();
    }

    public Match getMatchById(Long id) {
        return matchRepository.findById(id).orElse(null);
    }

    public List<Game> getGamesByMatchId(Long matchId) {
        return gameRepository.findByMatchId(matchId);
    }

    public Match saveMatch(Match match) {
        return matchRepository.save(match);
    }

    public void deleteMatch(Long id) {
        matchRepository.deleteById(id);
    }
}