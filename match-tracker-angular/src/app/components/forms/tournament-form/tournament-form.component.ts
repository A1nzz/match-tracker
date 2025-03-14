import { Component, Inject } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Tournament } from '../../../models/models';

@Component({
  selector: 'app-tournament-form',
  imports: [ReactiveFormsModule],
  templateUrl: './tournament-form.component.html',
  styleUrls: ['./tournament-form.component.scss'],
})
export class TournamentFormComponent {
  tournamentForm: FormGroup;
  isEditMode: boolean = false;

  constructor(
    private fb: FormBuilder,
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
    });

    // Если передан турнир (режим редактирования), заполняем форму
    if (data) {
      this.isEditMode = true;
      this.tournamentForm.patchValue(data);
    }
  }

  // Сохранить турнир
  saveTournament(): void {
    if (this.tournamentForm.valid) {
      this.dialogRef.close(this.tournamentForm.value);
    }
  }

  // Закрыть попап
  close(): void {
    this.dialogRef.close();
  }
}