import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { GamesService } from '../../../services/games.service';
import { GameFormComponent } from '../../forms/game-form/game-form.component';
import { Game } from '../../../models/models';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-admin-games',
  imports: [CommonModule],
  templateUrl: './admin-games.component.html',
  styleUrls: ['./admin-games.component.scss'],
})
export class AdminGamesComponent implements OnInit {
  games: Game[] = [];

  constructor(
    private gamesService: GamesService,
    private dialog: MatDialog
  ) {}

  ngOnInit(): void {
    this.loadGames();
  }

  // Загрузить список игр
  loadGames(): void {
    this.gamesService.getGames().subscribe({
      next: (data) => (this.games = data),
      error: (error) => console.error('Error fetching games:', error),
    });
  }

  // Открыть попап для добавления игры
  addGame(): void {
    const dialogRef = this.dialog.open(GameFormComponent, {
      data: null, // Передаём null, так как это режим добавления
    });

    dialogRef.afterClosed().subscribe((result) => {
      if (result) {
        this.gamesService.addGame(result).subscribe(() => this.loadGames());
      }
    });
  }

  // Открыть попап для редактирования игры
  editGame(game: Game): void {
    const dialogRef = this.dialog.open(GameFormComponent, {
      data: game, // Передаём игру для редактирования
    });

    dialogRef.afterClosed().subscribe((result) => {
      if (result) {
        result.id = game.id; // Добавляем ID для обновления
        this.gamesService.updateGame(result).subscribe(() => this.loadGames());
      }
    });
  }

  // Удалить игру
  deleteGame(gameId: number): void {
    this.gamesService.deleteGame(gameId).subscribe({
      next: () => this.loadGames(),
      error: (error) => console.error('Error deleting game:', error),
    });
  }
}