import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, RouterModule } from '@angular/router';
import { MatchGamesService } from '../../services/match-games.service';
import { Game } from '../../models/models';

@Component({
  selector: 'app-match-games',
  imports: [RouterModule],
  templateUrl: './match-games.component.html',
})
export class MatchGamesComponent implements OnInit {
  games: Game[] = [];
  matchId: number | null = null;

  constructor(
    private route: ActivatedRoute,
    private matchGamesService: MatchGamesService
  ) {}

  ngOnInit(): void {
    // Получаем matchId из параметров маршрута
    this.matchId = +this.route.snapshot.paramMap.get('matchId')!;

    if (this.matchId) {
      // Загружаем игры для матча
      this.matchGamesService.getGames(this.matchId).subscribe({
        next: (data) => (this.games = data),
        error: (error) => console.error('Error fetching games:', error),
      });
    }
  }
}