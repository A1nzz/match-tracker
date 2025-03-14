import { Component, Inject } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Game, Match } from '../../../models/models';
import { MatchesService } from '../../../services/matches.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-game-form',
  imports: [ReactiveFormsModule, CommonModule],
  templateUrl: './game-form.component.html',
  styleUrls: ['./game-form.component.scss'],
})
export class GameFormComponent {
  gameForm: FormGroup;
  isEditMode: boolean = false;
  matches: Match[] = [];

  constructor(
    private fb: FormBuilder,
    public dialogRef: MatDialogRef<GameFormComponent>,
    @Inject(MAT_DIALOG_DATA) public data: Game | null,
    private matchesService: MatchesService
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