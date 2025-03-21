import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { CommonModule } from '@angular/common';
import { MatchType, Match, Team, Tournament } from '../../../models/models';
import { TournamentsService } from '../../../services/tournaments.service';
import { TeamsService } from '../../../services/teams.service';
import { MatchTypesService } from '../../../services/match-types.service';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatButtonModule } from '@angular/material/button';
import { MatOptionModule } from '@angular/material/core';

@Component({
  selector: 'app-match-form',
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MatInputModule,
    MatSelectModule,
    MatFormFieldModule,
    MatButtonModule,
    MatOptionModule,
  ],
  templateUrl: './match-form.component.html',
  styleUrls: ['./match-form.component.scss'],
})
export class MatchFormComponent implements OnInit {
  matchForm: FormGroup;
  isEditMode: boolean = false;
  teams: Team[] = []; 
  tournaments: Tournament[] = []; 
  matchTypes: MatchType[] = [];

  constructor(
    private readonly fb: FormBuilder,
    public dialogRef: MatDialogRef<MatchFormComponent>,
    @Inject(MAT_DIALOG_DATA) public data: Match | null,
    private readonly tournamentsService: TournamentsService,
    private readonly teamsService: TeamsService,
    private readonly matchTypeService: MatchTypesService
  ) {
    this.matchForm = this.fb.group({
      tournament: [null, Validators.required],
      teamRadiant: [null, Validators.required],
      teamDire: [null, Validators.required],
      matchType: [null, Validators.required],
      bestOf: [1, Validators.required],
      matchDate: ['', Validators.required],
    });
  }

  ngOnInit(): void {
    this.teamsService.getTeams().subscribe({
      next: (data) => (this.teams = data),
      error: (error) => console.error('Error fetching teams:', error),
    });

    this.tournamentsService.getTournaments().subscribe({
      next: (data) => (this.tournaments = data),
      error: (error) => console.error('Error fetching tournaments:', error),
    });

    this.matchTypeService.getMatchTypes().subscribe({
      next: (data) => (this.matchTypes = data),
      error: (error) => console.error('Error fetching types:', error),
    });

    if (this.data) {
      this.isEditMode = true;
      this.matchForm.patchValue(this.data);
    }
  }

  saveMatch(): void {
    if (this.matchForm.valid) {
      this.dialogRef.close(this.matchForm.value);
    }
  }

  // Закрыть попап
  close(): void {
    this.dialogRef.close();
  }
}