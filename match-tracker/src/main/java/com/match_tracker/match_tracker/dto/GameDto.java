package com.match_tracker.match_tracker.dto;

import com.match_tracker.match_tracker.entity.Match;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class GameDto {

    private Long id;

    private Match match;

    private Integer duration;

    private String winner;

    private LocalDateTime startTime;

    private Integer radiantScore;

    private Integer direScore;
}
