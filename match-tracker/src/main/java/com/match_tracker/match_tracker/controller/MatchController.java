package com.match_tracker.match_tracker.controller;

import com.match_tracker.match_tracker.entity.Game;
import com.match_tracker.match_tracker.entity.Match;
import com.match_tracker.match_tracker.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/matches")
public class MatchController {

    @Autowired
    private MatchService matchService;

    @GetMapping
    public List<Match> getAllMatches() {
        return matchService.getAllMatches();
    }

    @GetMapping("/{id}")
    public Match getMatchById(@PathVariable Long id) {
        return matchService.getMatchById(id);
    }

    @GetMapping("/{matchId}/games")
    public List<Game> getGamesByMatchId(@PathVariable Long matchId) {
        return matchService.getGamesByMatchId(matchId);
    }
}