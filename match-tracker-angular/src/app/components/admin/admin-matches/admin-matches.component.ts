import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatchesService } from '../../../services/matches.service';
import { MatchFormComponent } from '../../forms/match-form/match-form.component';
import { CommonModule } from '@angular/common';
import { Match } from '../../../models/models';

@Component({
  selector: 'app-admin-matches',
  imports: [CommonModule],
  templateUrl: './admin-matches.component.html',
  styleUrls: ['./admin-matches.component.scss'],
})
export class AdminMatchesComponent implements OnInit {
  matches: Match[] = [];

  constructor(
    private readonly matchesService: MatchesService,
    private readonly dialog: MatDialog
  ) {}

  ngOnInit(): void {
    this.loadMatches();
  }

  // Загрузить список матчей
  loadMatches(): void {
    this.matchesService.getMatches().subscribe({
      next: (data) => (this.matches = data),
      error: (error) => console.error('Error fetching matches:', error),
    });
  }

  // Открыть попап для добавления матча
  addMatch(): void {
    const dialogRef = this.dialog.open(MatchFormComponent, {
      data: null, // Передаём null, так как это режим добавления
    });

    dialogRef.afterClosed().subscribe((result) => {
      if (result) {
        this.matchesService.addMatch(result).subscribe((newMatch) => {
          this.matches.push(newMatch); // Добавляем новый матч в локальный массив
        });
      }
    });
  }

  // Открыть попап для редактирования матча
  editMatch(match: Match): void {
    const dialogRef = this.dialog.open(MatchFormComponent, {
      data: match, // Передаём матч для редактирования
    });

    dialogRef.afterClosed().subscribe((result) => {
      if (result) {
        result.id = match.id; // Добавляем ID для обновления
        this.matchesService.updateMatch(result).subscribe((updatedMatch) => {
          const index = this.matches.findIndex(m => m.id === updatedMatch.id);
          if (index !== -1) {
            this.matches[index] = updatedMatch; // Обновляем существующий матч в локальном массиве
          }
        });
      }
    });
  }

  // Удалить матч
  deleteMatch(matchId: number): void {
    this.matchesService.deleteMatch(matchId).subscribe({
      next: () => {
        this.matches = this.matches.filter(m => m.id !== matchId); // Удаляем матч из локального массива
      },
      error: (error) => console.error('Error deleting match:', error),
    });
  }
}