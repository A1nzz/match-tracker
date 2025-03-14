import { Routes } from '@angular/router';
import { HeroesComponent } from './components/heroes/heroes.component';
import { ItemsComponent } from './components/items/items.component';
import { TeamsComponent } from './components/teams/teams.component';
import { TournamentsComponent } from './components/tournaments/tournaments.component';
import { TournamentMatchesComponent } from './components/tournament-matches/tournament-matches.component';
import { MatchGamesComponent } from './components/match-games/match-games.component';
import { GameStatsComponent } from './components/game-stats/game-stats.component';
import { HomeComponent } from './components/home/home.component';
import { AdminHeroesComponent } from './components/admin/admin-heroes/admin-heroes.component';
import { AdminItemsComponent } from './components/admin/admin-items/admin-items.component';
import { AdminTeamsComponent } from './components/admin/admin-teams/admin-teams.component';
import { AdminPlayersComponent } from './components/admin/admin-players/admin-players.component';
import { AdminTournamentsComponent } from './components/admin/admin-tournaments/admin-tournaments.component';
import { AdminMatchesComponent } from './components/admin/admin-matches/admin-matches.component';
import { AdminGamesComponent } from './components/admin/admin-games/admin-games.component';
import { AdminPlayerHeroComponent } from './components/admin/admin-player-hero/admin-player-hero.component';

export const appRoutes: Routes = [
  { path: 'heroes', component: HeroesComponent },
  { path: 'items', component: ItemsComponent },
  { path: 'teams', component: TeamsComponent },
  { path: 'tournaments', component: TournamentsComponent },
  { path: 'tournaments/:tournamentId/matches', component: TournamentMatchesComponent },
  { path: 'matches/:matchId/games', component: MatchGamesComponent },
  { path: 'games/:gameId/stats', component: GameStatsComponent },
  { path: '', component: HomeComponent },

  // Админские маршруты
  { path: 'admin/heroes', component: AdminHeroesComponent },
  { path: 'admin/items', component: AdminItemsComponent },
  { path: 'admin/teams', component: AdminTeamsComponent },
  { path: 'admin/players', component: AdminPlayersComponent },
  { path: 'admin/tournaments', component: AdminTournamentsComponent },
  { path: 'admin/matches', component: AdminMatchesComponent },
  { path: 'admin/games', component: AdminGamesComponent },
  { path: 'admin/player-heroes', component: AdminPlayerHeroComponent },
  //{ path: 'admin/game-stats', component: AdminGameStatsComponent },
];


