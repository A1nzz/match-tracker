package com.match_tracker.match_tracker.controller;

import com.match_tracker.match_tracker.entity.GameStats;
import com.match_tracker.match_tracker.service.GameStatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/game-stats")
public class AdminGameStatsController {

    private final GameStatsService gameStatsService;

    @Autowired
    public AdminGameStatsController(GameStatsService gameStatsService) {
        this.gameStatsService = gameStatsService;
    }

    @GetMapping
    public List<GameStats> getAllGameStats() {
        return gameStatsService.getAllGameStats();
    }

    @PostMapping
    public GameStats createGameStats(@RequestBody GameStats gameStats) {
        return gameStatsService.saveGameStats(gameStats);
    }

    @PutMapping("/{id}")
    public GameStats updateGameStats(@PathVariable Long id, @RequestBody GameStats gameStats) {
        gameStats.setId(id);
        return gameStatsService.saveGameStats(gameStats);
    }

    @DeleteMapping("/{id}")
    public void deleteGameStats(@PathVariable Long id) {
        gameStatsService.deleteGameStats(id);
    }
}