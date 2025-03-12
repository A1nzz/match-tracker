package com.match_tracker.match_tracker.controller;

import com.match_tracker.match_tracker.entity.GameStats;
import com.match_tracker.match_tracker.service.GameItemStatsService;
import com.match_tracker.match_tracker.service.GameStatsService;
import com.match_tracker.match_tracker.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/admin/game-stats")
public class AdminGameStatsController {

    @Autowired
    private GameStatsService gameStatsService;

    @Autowired
    private GameItemStatsService gameItemStatsService;

    @Autowired
    private ItemService itemService;

    @GetMapping
    public List<GameStats> getAllGameStats() {
        return gameStatsService.getAllGameStats();

    }

    @PutMapping
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

