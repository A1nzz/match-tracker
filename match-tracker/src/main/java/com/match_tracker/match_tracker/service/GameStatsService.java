package com.match_tracker.match_tracker.service;

import com.match_tracker.match_tracker.entity.GameStats;
import com.match_tracker.match_tracker.repository.GameStatsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameStatsService {

    @Autowired
    private GameStatsRepository gameStatsRepository;

    public List<GameStats> getAllGameStats() {
        return gameStatsRepository.findAll();
    }

    public GameStats getGameStatsById(Long id) {
        return gameStatsRepository.findById(id).orElse(null);
    }

    public GameStats saveGameStats(GameStats gameStats) {
        return gameStatsRepository.save(gameStats);
    }

    public void deleteGameStats(Long id) {
        gameStatsRepository.deleteById(id);
    }
}
