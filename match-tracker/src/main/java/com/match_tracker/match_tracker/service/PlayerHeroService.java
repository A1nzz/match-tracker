package com.match_tracker.match_tracker.service;

import com.match_tracker.match_tracker.entity.PlayerHero;
import com.match_tracker.match_tracker.repository.PlayerHeroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerHeroService {

    @Autowired
    private PlayerHeroRepository playerHeroRepository;

    public List<PlayerHero> getAllPlayerHeroes() {
        return playerHeroRepository.findAll();
    }

    public PlayerHero getPlayerHeroById(Long id) {
        return playerHeroRepository.findById(id).orElse(null);
    }

    public PlayerHero savePlayerHero(PlayerHero playerHero) {
        return playerHeroRepository.save(playerHero);
    }

    public void deletePlayerHero(Long id) {
        playerHeroRepository.deleteById(id);
    }
}
