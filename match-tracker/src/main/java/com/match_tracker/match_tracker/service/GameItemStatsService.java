package com.match_tracker.match_tracker.service;

import com.match_tracker.match_tracker.entity.GameItemStats;
import com.match_tracker.match_tracker.repository.GameItemStatsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameItemStatsService {

    @Autowired
    private GameItemStatsRepository gameItemStatsRepository;

    public List<GameItemStats> getAllGameItemStats() {
        return gameItemStatsRepository.findAll();
    }

    public GameItemStats getGameItemStatsById(Long id) {
        return gameItemStatsRepository.findById(id).orElse(null);
    }

    public GameItemStats saveGameItemStats(GameItemStats gameItemStats) {
        return gameItemStatsRepository.save(gameItemStats);
    }

    public void deleteGameItemStats(Long id) {
        gameItemStatsRepository.deleteById(id);
    }
}
