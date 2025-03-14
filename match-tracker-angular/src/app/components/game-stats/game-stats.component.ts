import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { GameStatsService } from '../../services/game-stats.service';
import { GameStat } from '../../models/models';

@Component({
  selector: 'app-game-stats',
  templateUrl: './game-stats.component.html',
  styleUrls: ['./game-stats.component.scss'],
})
export class GameStatsComponent implements OnInit {
  stats: GameStat[] = [];
  gameId: number | null = null;

  radiantStats: GameStat[] = [];
  direStats: GameStat[] = [];

  constructor(
    private route: ActivatedRoute,
    private gameStatsService: GameStatsService
  ) {}

  ngOnInit(): void {
    // Получаем gameId из параметров маршрута
    this.gameId = +this.route.snapshot.paramMap.get('gameId')!;

    if (this.gameId) {
      // Загружаем статистику для игры
      this.gameStatsService.getGameStats(this.gameId).subscribe({
        next: (data) => {
          this.stats = data;
          // Разделяем статистику на Radiant и Dire
          this.radiantStats = this.stats.filter(
            (stat) =>
              stat.gameStats.game.match.teamRadiant.name ===
              stat.gameStats.playerHero.player.team.name
          );
          this.direStats = this.stats.filter(
            (stat) =>
              stat.gameStats.game.match.teamDire.name === stat.gameStats.playerHero.player.team.name
          );
        },
        error: (error) => console.error('Error fetching game stats:', error),
      });
    }
  }
}