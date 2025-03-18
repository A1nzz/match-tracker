package com.match_tracker.match_tracker.controller;

import com.match_tracker.match_tracker.dto.GameDto;
import com.match_tracker.match_tracker.dto.MatchDto;
import com.match_tracker.match_tracker.entity.Match;
import com.match_tracker.match_tracker.entity.MatchWithScores;
import com.match_tracker.match_tracker.service.MatchService;
import com.match_tracker.match_tracker.service.MatchWithScoresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/matches")
public class MatchController {

    private final MatchService matchService;
    private final MatchWithScoresService matchWithScoresService;

    @Autowired
    public MatchController(MatchService matchService, MatchWithScoresService matchWithScoresService) {
        this.matchService = matchService;
        this.matchWithScoresService = matchWithScoresService;
    }

    @GetMapping
    public List<MatchDto> getAllMatches() {
        return matchWithScoresService.getAllMatches();
    }

    @GetMapping("/{id}")
    public Match getMatchById(@PathVariable Long id) {
        return matchService.getMatchById(id);
    }

    @GetMapping("/{matchId}/games")
    public List<GameDto> getGamesByMatchId(@PathVariable Long matchId) {
        return matchService.getGamesByMatchId(matchId);
    }
}