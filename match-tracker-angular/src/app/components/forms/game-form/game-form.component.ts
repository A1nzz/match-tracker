import { Component, Inject } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Game, Match } from '../../../models/models';
import { MatchesService } from '../../../services/matches.service';
import { CommonModule } from '@angular/common';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatButtonModule } from '@angular/material/button';
import { MatOptionModule } from '@angular/material/core';

@Component({
  selector: 'app-game-form',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MatInputModule,
    MatSelectModule,
    MatFormFieldModule,
    MatButtonModule,
    MatOptionModule,
  ],
  templateUrl: './game-form.component.html',
  styleUrls: ['./game-form.component.scss'],
})
export class GameFormComponent {
  gameForm: FormGroup;
  isEditMode: boolean = false;
  matches: Match[] = [];

  constructor(
    private readonly fb: FormBuilder,
    public dialogRef: MatDialogRef<GameFormComponent>,
    @Inject(MAT_DIALOG_DATA) public data: Game | null,
    private readonly matchesService: MatchesService
  ) {
    this.gameForm = this.fb.group({
      match: [null, Validators.required],
      winner: ['', Validators.required],
      duration: [0, [Validators.required, Validators.min(0)]],
      startTime: ['', Validators.required],
    });
  }

  ngOnInit(): void {
    this.matchesService.getMatches().subscribe({
      next: (data) => (this.matches = data),
      error: (error) => console.error('Error fetching matches:', error),
    });

    if (this.data) {
      this.isEditMode = true;
      this.gameForm.patchValue(this.data);
    }
  }

  // Сохранить игру
  saveGame(): void {
    if (this.gameForm.valid) {
      this.dialogRef.close(this.gameForm.value);
    }
  }

  // Закрыть попап
  close(): void {
    this.dialogRef.close();
  }
}