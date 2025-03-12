package com.match_tracker.match_tracker.controller;

import com.match_tracker.match_tracker.entity.Hero;
import com.match_tracker.match_tracker.service.HeroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/heroes")
public class AdminHeroController {

    @Autowired
    private HeroService heroService;

    @GetMapping
    public List<Hero> getAllHeroes() {
        return heroService.getAllHeroes();
    }

    @PostMapping
    public Hero createHero(@RequestBody Hero hero) {
        return heroService.saveHero(hero);
    }

    @PutMapping("/{id}")
    public Hero updateHero(@PathVariable Long id, @RequestBody Hero hero) {
        hero.setId(id);
        return heroService.saveHero(hero);
    }

    @DeleteMapping("/{id}")
    public void deleteHero(@PathVariable Long id) {
        heroService.deleteHero(id);
    }
}