package com.match_tracker.match_tracker.controller;

import com.match_tracker.match_tracker.entity.GameItemStats;
import com.match_tracker.match_tracker.service.GameItemStatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/game-item-stats")
public class GameItemStatsController {

    private final GameItemStatsService gameItemStatsService;

    @Autowired
    public GameItemStatsController(GameItemStatsService gameItemStatsService) {
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
}