package com.match_tracker.match_tracker.controller;

import com.match_tracker.match_tracker.entity.PlayerHero;
import com.match_tracker.match_tracker.service.PlayerHeroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/player-heroes")
public class PlayerHeroController {

    @Autowired
    private PlayerHeroService playerHeroService;

    @GetMapping
    public List<PlayerHero> getAllPlayerHeroes() {
        return playerHeroService.getAllPlayerHeroes();
    }

    @GetMapping("/{id}")
    public PlayerHero getPlayerHeroById(@PathVariable Long id) {
        return playerHeroService.getPlayerHeroById(id);
    }
}
