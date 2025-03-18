import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { PlayerHeroService } from '../../../services/player-hero.service';
import { PlayerHeroFormComponent } from '../../forms/player-hero-form/player-hero-form.component';
import { PlayerHero } from '../../../models/models';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-admin-player-hero',
  imports: [CommonModule],
  templateUrl: './admin-player-hero.component.html',
  styleUrls: ['./admin-player-hero.component.scss'],
})
export class AdminPlayerHeroComponent implements OnInit {
  playerHeroes: PlayerHero[] = [];

  constructor(
    private playerHeroService: PlayerHeroService,
    private dialog: MatDialog
  ) {}

  ngOnInit(): void {
    this.loadPlayerHeroes();
  }

  // Загрузить список связей
  loadPlayerHeroes(): void {
    this.playerHeroService.getPlayerHeroes().subscribe({
      next: (data) => (this.playerHeroes = data),
      error: (error) => console.error('Error fetching player heroes:', error),
    });
  }

  // Открыть попап для добавления связи
  addPlayerHero(): void {
    const dialogRef = this.dialog.open(PlayerHeroFormComponent, {
      data: null, // Передаём null, так как это режим добавления
    });

    dialogRef.afterClosed().subscribe((result) => {
      if (result) {
        this.playerHeroService.addPlayerHero(result).subscribe(() => this.loadPlayerHeroes());
      }
    });
  }

  // Открыть попап для редактирования связи
  editPlayerHero(playerHero: PlayerHero): void {
    const dialogRef = this.dialog.open(PlayerHeroFormComponent, {
      data: playerHero, // Передаём связь для редактирования
    });

    dialogRef.afterClosed().subscribe((result) => {
      if (result) {
        result.id = playerHero.id; // Добавляем ID для обновления
        this.playerHeroService.updatePlayerHero(result).subscribe(() => this.loadPlayerHeroes());
      }
    });
  }

  // Удалить связь
  deletePlayerHero(playerHeroId: number): void {
    this.playerHeroService.deletePlayerHero(playerHeroId).subscribe({
      next: () => this.loadPlayerHeroes(),
      error: (error) => console.error('Error deleting player hero:', error),
    });
  }
}