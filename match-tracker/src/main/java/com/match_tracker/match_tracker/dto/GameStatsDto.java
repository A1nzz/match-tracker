package com.match_tracker.match_tracker.dto;

import com.match_tracker.match_tracker.entity.GameStats;

import java.util.List;

public class GameStatsDto {
    private Long id;
    private GameStats gameStats;
    private List<ItemDto> items;

    // Конструктор
    public GameStatsDto(GameStats gameStats, List<ItemDto> items) {
        this.id = gameStats.getId();
        this.gameStats = gameStats;
        this.items = items;
    }

    // Геттеры
    public Long getId() {
        return id;
    }
    public GameStats getGameStats() {
        return gameStats;
    }

    public List<ItemDto> getItems() {
        return items;
    }

    // Сеттеры
    public void setId(Long id) {
        this.id = id;
    }

    public void setGameStats(GameStats gameStats) {
        this.gameStats = gameStats;
    }

    public void setItems(List<ItemDto> items) {
        this.items = items;
    }


}
