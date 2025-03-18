import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { AdminGameStatsService } from '../../../services/admin-game-stats.service';
import { GameStatFormComponent } from '../../forms/game-stat-form/game-stat-form.component';
import { GameStats } from '../../../models/models';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-admin-game-stats',
  imports: [CommonModule],
  templateUrl: './admin-game-stats.component.html',
  styleUrls: ['./admin-game-stats.component.scss'],
})
export class AdminGameStatsComponent implements OnInit {
  gameStats: GameStats[] = [];

  constructor(
    private gameStatsService: AdminGameStatsService,
    private dialog: MatDialog
  ) {}

  ngOnInit(): void {
    this.loadGameStats();
  }

  // Загрузить список игровой статистики
  loadGameStats(): void {
    this.gameStatsService.getGameStats().subscribe({
      next: (data) => (this.gameStats = data),
      error: (error) => console.error('Error fetching game stats:', error),
    });
  }

  // Открыть попап для добавления игровой статистики
  addGameStat(): void {
    const dialogRef = this.dialog.open(GameStatFormComponent, {
      data: null, // Передаём null, так как это режим добавления
    });

    dialogRef.afterClosed().subscribe((result) => {
      if (result) {
        this.gameStatsService.addGameStat(result).subscribe(() => this.loadGameStats());
      }
    });
  }

  // Открыть попап для редактирования игровой статистики
  editGameStat(gameStat: GameStats): void {
    const dialogRef = this.dialog.open(GameStatFormComponent, {
      data: gameStat, // Передаём игровую статистику для редактирования
    });

    dialogRef.afterClosed().subscribe((result) => {
      if (result) {
        result.id = gameStat.id; // Добавляем ID для обновления
        this.gameStatsService.updateGameStat(result).subscribe(() => this.loadGameStats());
      }
    });
  }

  // Удалить игровую статистику
  deleteGameStat(gameStatId: number): void {
    this.gameStatsService.deleteGameStat(gameStatId).subscribe({
      next: () => this.loadGameStats(),
      error: (error) => console.error('Error deleting game stat:', error),
    });
  }
}