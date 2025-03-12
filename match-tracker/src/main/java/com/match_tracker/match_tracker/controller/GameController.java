package com.match_tracker.match_tracker.controller;

import com.match_tracker.match_tracker.dto.GameStatsDto;
import com.match_tracker.match_tracker.dto.ItemDto;
import com.match_tracker.match_tracker.entity.Game;
import com.match_tracker.match_tracker.entity.GameStats;
import com.match_tracker.match_tracker.service.GameItemStatsService;
import com.match_tracker.match_tracker.service.GameService;
import com.match_tracker.match_tracker.service.GameStatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/games")
public class GameController {

    @Autowired
    private GameService gameService;

    @Autowired
    private GameItemStatsService gameItemStatsService;

    @Autowired
    private GameStatsService gameStatsService;

    @GetMapping
    public List<Game> getAllGames() {
        return gameService.getAllGames();
    }

    @GetMapping("/{id}")
    public Game getGameById(@PathVariable Long id) {
        return gameService.getGameById(id);
    }


    @GetMapping("/{gameId}/stats")
    public ResponseEntity<List<GameStatsDto>> getGameStats(@PathVariable Long gameId) {
        List<GameStats> stats = gameStatsService.getStatsByGameId(gameId);
        List<GameStatsDto> statsDto = stats.stream()
                .map(stat -> {
                    List<ItemDto> items = gameItemStatsService.getItemsByGameStatsId(stat.getId());
                    return new GameStatsDto(stat, items);
                })
                .collect(Collectors.toList());
        return ResponseEntity.ok(statsDto);
    }
}
