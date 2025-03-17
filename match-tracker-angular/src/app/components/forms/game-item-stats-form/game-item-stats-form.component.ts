import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { GameItemStats } from '../../../models/models';
import { ItemsService } from '../../../services/items.service';
import { AdminGameStatsService } from '../../../services/admin-game-stats.service';
import { CommonModule } from '@angular/common';


@Component({
  selector: 'app-game-item-stats-form',
  imports: [ReactiveFormsModule, CommonModule],
  templateUrl: './game-item-stats-form.component.html',
  styleUrls: ['./game-item-stats-form.component.scss'],
})
export class GameItemStatsFormComponent implements OnInit {
  gameItemStatsForm: FormGroup;
  isEditMode: boolean = false;
  gameStats: any[] = []; 
  items: any[] = [];


  constructor(
    private fb: FormBuilder,
    public dialogRef: MatDialogRef<GameItemStatsFormComponent>,
    @Inject(MAT_DIALOG_DATA) public data: GameItemStats | null,
    private gameStatsService: AdminGameStatsService, 
    private itemsService: ItemsService,
  ) {
    // Инициализация формы
    this.gameItemStatsForm = this.fb.group({
      gameStatsId: [null, Validators.required],
      itemId: [null, Validators.required],
      quantity: [1, [Validators.required, Validators.min(1)]],
    });
  }

  ngOnInit(): void {
    // Загружаем список игр, предметов и игроков
    this.gameStatsService.getGameStats().subscribe({
      next: (data) => (this.gameStats = data),
      error: (error) => console.error('Error fetching game stats:', error),
    });

    this.itemsService.getItems().subscribe({
      next: (data) => (this.items = data),
      error: (error) => console.error('Error fetching items:', error),
    });

    // Если передана статистика (режим редактирования), заполняем форму
    if (this.data) {
      this.isEditMode = true;
      this.gameItemStatsForm.patchValue(this.data);
    }
  }

  // Сохранить статистику предмета в игре
  saveGameItemStat(): void {
    if (this.gameItemStatsForm.valid) {
      this.dialogRef.close(this.gameItemStatsForm.value);
    }
  }

  // Закрыть попап
  close(): void {
    this.dialogRef.close();
  }
}