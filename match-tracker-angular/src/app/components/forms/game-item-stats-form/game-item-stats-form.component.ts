import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { GameItemStats } from '../../../models/models';
import { ItemsService } from '../../../services/items.service';
import { AdminGameStatsService } from '../../../services/admin-game-stats.service';
import { CommonModule } from '@angular/common';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatButtonModule } from '@angular/material/button';
import { MatOptionModule } from '@angular/material/core';


@Component({
  selector: 'app-game-item-stats-form',
  imports: [ReactiveFormsModule, CommonModule,
    MatInputModule,
    MatSelectModule,
    MatFormFieldModule,
    MatButtonModule,
    MatOptionModule,
  ],
  templateUrl: './game-item-stats-form.component.html',
  styleUrls: ['./game-item-stats-form.component.scss'],
})
export class GameItemStatsFormComponent implements OnInit {
  gameItemStatsForm: FormGroup;
  isEditMode: boolean = false;
  gameStatsList: any[] = []; 
  items: any[] = [];


  constructor(
    private readonly fb: FormBuilder,
    public dialogRef: MatDialogRef<GameItemStatsFormComponent>,
    @Inject(MAT_DIALOG_DATA) public data: GameItemStats | null,
    private readonly gameStatsService: AdminGameStatsService, 
    private readonly itemsService: ItemsService,
  ) {
    // Инициализация формы
    this.gameItemStatsForm = this.fb.group({
      gameStats: [null, Validators.required],
      item: [null, Validators.required],
      quantity: [1, [Validators.required, Validators.min(1)]],
    });
  }

  ngOnInit(): void {
    // Загружаем список игр, предметов и игроков
    this.gameStatsService.getGameStats().subscribe({
      next: (data) => (this.gameStatsList = data),
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