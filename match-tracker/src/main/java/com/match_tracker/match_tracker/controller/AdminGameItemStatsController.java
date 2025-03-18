package com.match_tracker.match_tracker.controller;

import com.match_tracker.match_tracker.entity.GameItemStats;
import com.match_tracker.match_tracker.service.GameItemStatsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/game-item-stats")
public class AdminGameItemStatsController {

    private final GameItemStatsService gameItemStatsService;

    public AdminGameItemStatsController(GameItemStatsService gameItemStatsService) {
        this.gameItemStatsService = gameItemStatsService;
    }

    @GetMapping
    public List<GameItemStats> getAllGameItemStats() {
        return gameItemStatsService.getAllGameItemStats();
    }

    @GetMapping("/{id}")
    public GameItemStats getGameItemStatsById(@PathVariable Long id) {
        return gameItemStatsService.getGameItemStatsById(id);
    }

    @PostMapping
    public GameItemStats createGameItemStats(@RequestBody GameItemStats gameItemStats) {
        return gameItemStatsService.saveGameItemStats(gameItemStats);
    }

    @PutMapping("/{id}")
    public GameItemStats updateGameItemStats(@PathVariable Long id, @RequestBody GameItemStats gameItemStats) {
        gameItemStats.setId(id);
        return gameItemStatsService.saveGameItemStats(gameItemStats);
    }

    @DeleteMapping("/{id}")
    public void deleteGameItemStats(@PathVariable Long id) {
        gameItemStatsService.deleteGameItemStats(id);
    }
}