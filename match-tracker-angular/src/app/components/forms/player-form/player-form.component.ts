import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { TeamsService } from '../../../services/teams.service';
import { Player, Team } from '../../../models/models';
import { CommonModule } from '@angular/common';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatButtonModule } from '@angular/material/button';
import { MatOptionModule } from '@angular/material/core';

@Component({
  selector: 'app-player-form',
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MatInputModule,
    MatSelectModule,
    MatFormFieldModule,
    MatButtonModule,
    MatOptionModule,
  ],
  templateUrl: './player-form.component.html',
  styleUrls: ['./player-form.component.scss'],
})
export class PlayerFormComponent implements OnInit {
  playerForm: FormGroup;
  isEditMode: boolean = false;
  teams: Team[] = []; // Список команд

  constructor(
    private fb: FormBuilder,
    public dialogRef: MatDialogRef<PlayerFormComponent>,
    @Inject(MAT_DIALOG_DATA) public data: Player | null,
    private teamsService: TeamsService
  ) {
    // Инициализация формы
    this.playerForm = this.fb.group({
      nickname: ['', Validators.required],
      realName: ['', Validators.required],
      team: [null, Validators.required],
      country: ['', Validators.required],
      role: ['', Validators.required],
    });
  }

  ngOnInit(): void {
    // Загружаем список команд при инициализации компонента
    this.teamsService.getTeams().subscribe({
      next: (data) => (this.teams = data),
      error: (error) => console.error('Error fetching teams:', error),
    });

    // Если передан игрок (режим редактирования), заполняем форму
    if (this.data) {
      this.isEditMode = true;
      this.playerForm.patchValue(this.data);
    }
  }

  // Сохранить игрока
  savePlayer(): void {
    if (this.playerForm.valid) {
      this.dialogRef.close(this.playerForm.value);
    }
  }

  // Закрыть попап
  close(): void {
    this.dialogRef.close();
  }
}