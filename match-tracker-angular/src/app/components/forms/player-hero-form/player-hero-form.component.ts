import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { PlayerHero, Player, Hero } from '../../../models/models';
import { PlayersService } from '../../../services/players.service';
import { HeroesService } from '../../../services/heroes.service';
import { CommonModule } from '@angular/common';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatButtonModule } from '@angular/material/button';
import { MatOptionModule } from '@angular/material/core';

@Component({
  selector: 'app-player-hero-form',
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MatInputModule,
    MatSelectModule,
    MatFormFieldModule,
    MatButtonModule,
    MatOptionModule,
  ],
  templateUrl: './player-hero-form.component.html',
  styleUrls: ['./player-hero-form.component.scss'],
})
export class PlayerHeroFormComponent implements OnInit {
  playerHeroForm: FormGroup;
  isEditMode: boolean = false;
  players: Player[] = []; // Список игроков
  heroes: Hero[] = []; // Список героев

  constructor(
    private fb: FormBuilder,
    public dialogRef: MatDialogRef<PlayerHeroFormComponent>,
    @Inject(MAT_DIALOG_DATA) public data: PlayerHero | null,
    private playersService: PlayersService, // Сервис для получения игроков
    private heroesService: HeroesService // Сервис для получения героев
  ) {
    // Инициализация формы
    this.playerHeroForm = this.fb.group({
      player: [null, Validators.required],
      hero: [null, Validators.required],
      gamesPlayed: [0, [Validators.required, Validators.min(0)]],
    });
  }

  ngOnInit(): void {
    // Загружаем список игроков и героев
    this.playersService.getPlayers().subscribe({
      next: (data) => (this.players = data),
      error: (error) => console.error('Error fetching players:', error),
    });

    this.heroesService.getHeroes().subscribe({
      next: (data) => (this.heroes = data),
      error: (error) => console.error('Error fetching heroes:', error),
    });

    // Если передана связь (режим редактирования), заполняем форму
    if (this.data) {
      this.isEditMode = true;
      this.playerHeroForm.patchValue(this.data);
    }
  }

  // Сохранить связь
  savePlayerHero(): void {
    if (this.playerHeroForm.valid) {
      this.dialogRef.close(this.playerHeroForm.value);
    }
  }

  // Закрыть попап
  close(): void {
    this.dialogRef.close();
  }
}