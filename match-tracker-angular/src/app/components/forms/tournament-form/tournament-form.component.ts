import { Component, Inject } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Tournament } from '../../../models/models';
import { CommonModule } from '@angular/common';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatButtonModule } from '@angular/material/button';
import { MatOptionModule } from '@angular/material/core';
import { dateRangeValidator } from '../../../utils/dateRangeValidator'; // Импортируем кастомный валидатор


@Component({
  selector: 'app-tournament-form',
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MatInputModule,
    MatSelectModule,
    MatFormFieldModule,
    MatButtonModule,
    MatOptionModule,
  ],
  templateUrl: './tournament-form.component.html',
  styleUrls: ['./tournament-form.component.scss'],
})
export class TournamentFormComponent {
  tournamentForm: FormGroup;
  isEditMode: boolean = false;

  constructor(
    private readonly fb: FormBuilder,
    public dialogRef: MatDialogRef<TournamentFormComponent>,
    @Inject(MAT_DIALOG_DATA) public data: Tournament | null
  ) {
    // Инициализация формы
    this.tournamentForm = this.fb.group({
      name: ['', Validators.required],
      prizePool: [0, [Validators.required, Validators.min(0)]],
      startDate: ['', Validators.required],
      endDate: ['', Validators.required],
      organizer: ['', Validators.required],
    }, { validators: dateRangeValidator() });

    // Если передан турнир (режим редактирования), заполняем форму
    if (data) {
      this.isEditMode = true;
      this.tournamentForm.patchValue(data);
    }
  }

  // Сохранить турнир
  saveTournament(): void {
    console.log('Form Value:', this.tournamentForm.value); // Логируем значения формы

    console.log('Form Errors:', this.tournamentForm.errors); // Логируем ошибки формы

    if (this.tournamentForm.valid) {
      this.dialogRef.close(this.tournamentForm.value);
    }
  }

  // Закрыть попап
  close(): void {
    this.dialogRef.close();
  }
}