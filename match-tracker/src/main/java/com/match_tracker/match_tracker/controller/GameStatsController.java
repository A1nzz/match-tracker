package com.match_tracker.match_tracker.controller;

import com.match_tracker.match_tracker.dto.GameStatsDto;
import com.match_tracker.match_tracker.entity.GameStats;
import com.match_tracker.match_tracker.service.GameStatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/game-stats")
public class GameStatsController {

    private final GameStatsService gameStatsService;

    @Autowired
    public GameStatsController(GameStatsService gameStatsService) {
        this.gameStatsService = gameStatsService;
    }

    @GetMapping
    public List<GameStatsDto> getAllGameStats() {
        return gameStatsService.getAllGameStatsDto();
    }

    @GetMapping("/{id}")
    public GameStats getGameStatsById(@PathVariable Long id) {
        return gameStatsService.getGameStatsById(id);
    }
}