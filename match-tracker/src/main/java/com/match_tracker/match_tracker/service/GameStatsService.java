package com.match_tracker.match_tracker.service;

import com.match_tracker.match_tracker.dto.GameStatsDto;
import com.match_tracker.match_tracker.dto.ItemDto;
import com.match_tracker.match_tracker.entity.GameStats;
import com.match_tracker.match_tracker.repository.GameStatsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GameStatsService {

    @Autowired
    private GameStatsRepository gameStatsRepository;

    @Autowired
    private  GameItemStatsService gameItemStatsService;

    public List<GameStatsDto> getAllGameStatsDto() {
        List<GameStats> stats = gameStatsRepository.findAll();
        return stats.stream()
                .map(stat -> {
                    List<ItemDto> items = gameItemStatsService.getItemsByGameStatsId(stat.getId());
                    return new GameStatsDto(stat, items);
                })
                .collect(Collectors.toList());
    }

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

    public List<GameStats> getStatsByGameId(Long gameId) {
        return gameStatsRepository.findByGameId(gameId);
    }
}
