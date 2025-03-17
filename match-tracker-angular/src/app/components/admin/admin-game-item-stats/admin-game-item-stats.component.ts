import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { GameItemStats } from '../../../models/models';
import { GameItemStatsService } from '../../../services/game-item-stats.service';
import { GameItemStatsFormComponent } from '../../forms/game-item-stats-form/game-item-stats-form.component';
import { CommonModule } from '@angular/common';


@Component({
  selector: 'app-admin-game-item-stats',
  imports: [CommonModule],
  templateUrl: './admin-game-item-stats.component.html',
  styleUrls: ['./admin-game-item-stats.component.scss'],
})
export class AdminGameItemStatsComponent implements OnInit {
  gameItemStats: GameItemStats[] = [];

  constructor(
    private gameItemStatsService: GameItemStatsService,
    private dialog: MatDialog
  ) {}

  ngOnInit(): void {
    this.loadGameItemStats();
  }

  // Загрузить список статистики предметов в играх
  loadGameItemStats(): void {
    this.gameItemStatsService.getGameItemStats().subscribe({
      next: (data) => (this.gameItemStats = data),
      error: (error) => console.error('Error fetching game item stats:', error),
    });
  }

  // Открыть попап для добавления статистики
  addGameItemStat(): void {
    const dialogRef = this.dialog.open(GameItemStatsFormComponent, {
      width: '400px',
      data: null, // Передаём null, так как это режим добавления
    });

    dialogRef.afterClosed().subscribe((result) => {
      if (result) {
        this.gameItemStatsService.addGameItemStat(result).subscribe(() => this.loadGameItemStats());
      }
    });
  }

  // Открыть попап для редактирования статистики
  editGameItemStat(gameItemStat: GameItemStats): void {
    const dialogRef = this.dialog.open(GameItemStatsFormComponent, {
      width: '400px',
      data: gameItemStat, // Передаём статистику для редактирования
    });

    dialogRef.afterClosed().subscribe((result) => {
      if (result) {
        result.id = gameItemStat.id; // Добавляем ID для обновления
        this.gameItemStatsService.updateGameItemStat(result).subscribe(() => this.loadGameItemStats());
      }
    });
  }

  // Удалить статистику
  deleteGameItemStat(gameItemStatId: number): void {
    this.gameItemStatsService.deleteGameItemStat(gameItemStatId).subscribe({
      next: () => this.loadGameItemStats(),
      error: (error) => console.error('Error deleting game item stat:', error),
    });
  }
}