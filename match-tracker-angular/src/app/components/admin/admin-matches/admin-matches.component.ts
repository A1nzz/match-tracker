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
    private matchesService: MatchesService,
    private dialog: MatDialog
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
      width: '400px',
      data: null, // Передаём null, так как это режим добавления
    });

    dialogRef.afterClosed().subscribe((result) => {
      if (result) {
        this.matchesService.addMatch(result).subscribe(() => this.loadMatches());
      }
    });
  }

  // Открыть попап для редактирования матча
  editMatch(match: Match): void {
    const dialogRef = this.dialog.open(MatchFormComponent, {
      width: '400px',
      data: match, // Передаём матч для редактирования
    });

    dialogRef.afterClosed().subscribe((result) => {
      if (result) {
        result.id = match.id; // Добавляем ID для обновления
        this.matchesService.updateMatch(result).subscribe(() => this.loadMatches());
      }
    });
  }

  // Удалить матч
  deleteMatch(matchId: number): void {
    this.matchesService.deleteMatch(matchId).subscribe({
      next: () => this.loadMatches(),
      error: (error) => console.error('Error deleting match:', error),
    });
  }
}