package com.match_tracker.match_tracker.service;

import com.match_tracker.match_tracker.entity.GameWithScores;
import com.match_tracker.match_tracker.entity.Match;
import com.match_tracker.match_tracker.repository.GameWithScoresRepository;
import com.match_tracker.match_tracker.repository.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.match_tracker.match_tracker.dto.GameDto;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GameWithScoresService {

    private final GameWithScoresRepository gameWithScoresRepository;
    private final MatchRepository matchRepository;

    @Autowired
    public GameWithScoresService(GameWithScoresRepository gameWithScoresRepository, MatchRepository matchRepository) {
        this.gameWithScoresRepository = gameWithScoresRepository;
        this.matchRepository = matchRepository;
    }

    public List<GameDto> getAllGamesWithScores() {
        List<GameWithScores> games = gameWithScoresRepository.findAll();
        return games.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public List<GameDto> getGamesByMatchId(Long matchId) {
        List<GameWithScores> games = gameWithScoresRepository.findByMatchId(matchId);
        return games.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private GameDto convertToDTO(GameWithScores gameWithScores) {
        GameDto dto = new GameDto();
        dto.setId(gameWithScores.getGameId());
        dto.setDuration(gameWithScores.getDuration());
        dto.setWinner(gameWithScores.getWinner());
        dto.setStartTime(gameWithScores.getStartTime());
        dto.setRadiantScore(gameWithScores.getRadiantScore());
        dto.setDireScore(gameWithScores.getDireScore());
        Match match = matchRepository.findById(gameWithScores.getMatchId()).orElse(null);
        dto.setMatch(match);

        return dto;
    }
}
