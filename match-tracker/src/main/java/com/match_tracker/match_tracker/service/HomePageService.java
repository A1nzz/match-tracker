package com.match_tracker.match_tracker.service;

import com.match_tracker.match_tracker.dto.HomePageDto;
import com.match_tracker.match_tracker.entity.Tournament;
import com.match_tracker.match_tracker.entity.Team;
import com.match_tracker.match_tracker.entity.Hero;
import com.match_tracker.match_tracker.repository.TournamentRepository;
import com.match_tracker.match_tracker.repository.TeamRepository;
import com.match_tracker.match_tracker.repository.HeroRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HomePageService {

    private final TournamentRepository tournamentRepository;
    private final TeamRepository teamRepository;
    private final HeroRepository heroRepository;

    public HomePageService(TournamentRepository tournamentRepository, TeamRepository teamRepository, HeroRepository heroRepository) {
        this.tournamentRepository = tournamentRepository;
        this.teamRepository = teamRepository;
        this.heroRepository = heroRepository;
    }

    public HomePageDto getHomePageData() {
        HomePageDto homePageDTO = new HomePageDto();

        List<Tournament> tournaments = tournamentRepository.findAll();
        List<Team> teams = teamRepository.findAll();
        List<Hero> heroes = heroRepository.findAll();

        homePageDTO.setTournaments(tournaments);
        homePageDTO.setTeams(teams);
        homePageDTO.setHeroes(heroes);

        return homePageDTO;
    }
}