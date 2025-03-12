package com.match_tracker.match_tracker.service;

import com.match_tracker.match_tracker.dto.ItemDto;
import com.match_tracker.match_tracker.entity.GameItemStats;
import com.match_tracker.match_tracker.repository.GameItemStatsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    public List<ItemDto> getItemsByGameStatsId(Long gameStatsId) {
        // Получаем список предметов по идентификатору статистики игры
        List<GameItemStats> itemStats = gameItemStatsRepository.findByGameStatsId(gameStatsId);

        // Преобразуем предметы в DTO
        return itemStats.stream()
                .map(itemStat -> new ItemDto(
                        itemStat.getItem().getId(),
                        itemStat.getItem().getName(),
                        itemStat.getItem().getCost(),
                        itemStat.getItem().getDescription(),
                        itemStat.getItem().getLogoUrl(),
                        itemStat.getQuantity()
                ))
                .collect(Collectors.toList());
    }
}
