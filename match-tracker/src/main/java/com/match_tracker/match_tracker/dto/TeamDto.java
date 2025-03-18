package com.match_tracker.match_tracker.dto;

import com.match_tracker.match_tracker.entity.Player; // Импортируем сущность Player
import lombok.Data;

import java.util.List;

@Data
public class TeamDto {
    private Long id;
    private String name;
    private String region;
    private Double rating;
    private String logoUrl;
    private List<Player> players; // Список игроков
}