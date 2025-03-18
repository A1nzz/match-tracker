package com.match_tracker.match_tracker.controller;

import com.match_tracker.match_tracker.dto.MatchDto;
import com.match_tracker.match_tracker.entity.Match;
import com.match_tracker.match_tracker.entity.Tournament;
import com.match_tracker.match_tracker.service.TournamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tournaments")
public class TournamentController {

    private final TournamentService tournamentService;

    @Autowired
    public TournamentController(TournamentService tournamentService) {
        this.tournamentService = tournamentService;
    }

    @GetMapping
    public List<Tournament> getAllTournaments() {
        return tournamentService.getAllTournaments();
    }

    @GetMapping("/{id}")
    public Tournament getTournamentById(@PathVariable Long id) {
        return tournamentService.getTournamentById(id);
    }

    @GetMapping("/{tournamentId}/matches")
    public List<MatchDto> getMatchesByTournamentId(@PathVariable Long tournamentId) {
        return tournamentService.getMatchesByTournamentId(tournamentId);
    }
}