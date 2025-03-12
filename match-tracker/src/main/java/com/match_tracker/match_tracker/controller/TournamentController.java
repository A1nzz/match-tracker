package com.match_tracker.match_tracker.controller;

import com.match_tracker.match_tracker.entity.Match;
import com.match_tracker.match_tracker.entity.Tournament;
import com.match_tracker.match_tracker.service.TournamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tournaments")
public class TournamentController {

    @Autowired
    private TournamentService tournamentService;

    @GetMapping
    public List<Tournament> getAllTournaments() {
        return tournamentService.getAllTournaments();
    }

    @GetMapping("/{id}")
    public Tournament getTournamentById(@PathVariable Long id) {
        return tournamentService.getTournamentById(id);
    }

    @GetMapping("/{tournamentId}/matches")
    public List<Match> getMatchesByTournamentId(@PathVariable Long tournamentId) {
        return tournamentService.getMatchesByTournamentId(tournamentId);
    }
}
