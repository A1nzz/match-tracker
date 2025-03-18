package com.match_tracker.match_tracker.service;

import com.match_tracker.match_tracker.entity.Game;
import com.match_tracker.match_tracker.entity.GameStats;
import com.match_tracker.match_tracker.repository.GameRepository;
import com.match_tracker.match_tracker.repository.GameStatsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameService {

    private final GameRepository gameRepository;
    private final GameStatsRepository gameStatsRepository;

    @Autowired
    public GameService(GameRepository gameRepository, GameStatsRepository gameStatsRepository) {
        this.gameRepository = gameRepository;
        this.gameStatsRepository = gameStatsRepository;
    }

    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }

    public Game getGameById(Long id) {
        return gameRepository.findById(id).orElse(null);
    }

    public Game saveGame(Game game) {
        return gameRepository.save(game);
    }

    public void deleteGame(Long id) {
        gameRepository.deleteById(id);
    }

    public List<GameStats> getGameStats(Long gameId) {
        return gameStatsRepository.findByGameId(gameId);
    }
}