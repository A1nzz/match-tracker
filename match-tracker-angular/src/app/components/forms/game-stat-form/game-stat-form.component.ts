import { Component, Inject } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Game, GameStats, PlayerHero } from '../../../models/models';
import { CommonModule } from '@angular/common';
import { GamesService } from '../../../services/games.service';
import { PlayerHeroService } from '../../../services/player-hero.service';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatButtonModule } from '@angular/material/button';
import { MatOptionModule } from '@angular/material/core';

@Component({
  selector: 'app-game-stat-form',
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MatInputModule,
    MatSelectModule,
    MatFormFieldModule,
    MatButtonModule,
    MatOptionModule,
  ],
  templateUrl: './game-stat-form.component.html',
  styleUrls: ['./game-stat-form.component.scss'],
})
export class GameStatFormComponent {
  gameStatForm: FormGroup;
  isEditMode: boolean = false;
  games: Game[] = [];
  playerHeroes: PlayerHero[] = [];

  constructor(
    private fb: FormBuilder,
    public dialogRef: MatDialogRef<GameStatFormComponent>,
    @Inject(MAT_DIALOG_DATA) public data: GameStats | null,
    private gameService: GamesService,
    private playerHeroService: PlayerHeroService
  ) {
    // Инициализация формы
    this.gameStatForm = this.fb.group({
      game: [null, Validators.required],
      playerHero: [null, Validators.required],
      kills: [0, [Validators.required, Validators.min(0)]],
      deaths: [0, [Validators.required, Validators.min(0)]],
      assists: [0, [Validators.required, Validators.min(0)]],
      lastHits: [0, [Validators.required, Validators.min(0)]],
      goldPerMinute: [0, [Validators.required, Validators.min(0)]],
      xpPerMinute: [0, [Validators.required, Validators.min(0)]],
      netWorth: [0, [Validators.required, Validators.min(0)]],
      finalLevel: [1, [Validators.required, Validators.min(1)]],
    });
  }

  ngOnInit(): void {
    
    this.gameService.getGames().subscribe({
      next: (data) => (this.games = data),
      error: (error) => console.error('Error fetching games:', error),
    });

    this.playerHeroService.getPlayerHeroes().subscribe({
      next: (data) => (this.playerHeroes = data),
      error: (error) => console.error('Error fetching games:', error),
    });

    if (this.data) {
      this.isEditMode = true;
      this.gameStatForm.patchValue(this.data);
    }
  }

  // Сохранить игровую статистику
  saveGameStat(): void {
    if (this.gameStatForm.valid) {
      this.dialogRef.close(this.gameStatForm.value);
    }
  }

  // Закрыть попап
  close(): void {
    this.dialogRef.close();
  }
}