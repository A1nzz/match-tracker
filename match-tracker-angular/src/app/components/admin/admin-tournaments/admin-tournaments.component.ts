import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { TournamentsService } from '../../../services/tournaments.service';
import { TournamentFormComponent } from '../../forms/tournament-form/tournament-form.component';
import { CommonModule } from '@angular/common';
import { Tournament } from '../../../models/models';

@Component({
  selector: 'app-admin-tournaments',
  imports: [CommonModule],
  templateUrl: './admin-tournaments.component.html',
  styleUrls: ['./admin-tournaments.component.scss'],
})
export class AdminTournamentsComponent implements OnInit {
  tournaments: Tournament[] = [];

  constructor(
    private tournamentsService: TournamentsService,
    private dialog: MatDialog
  ) {}

  ngOnInit(): void {
    this.loadTournaments();
  }

  // Загрузить список турниров
  loadTournaments(): void {
    this.tournamentsService.getTournaments().subscribe({
      next: (data) => (this.tournaments = data),
      error: (error) => console.error('Error fetching tournaments:', error),
    });
  }

  // Открыть попап для добавления турнира
  addTournament(): void {
    const dialogRef = this.dialog.open(TournamentFormComponent, {
      width: '400px',
      data: null, // Передаём null, так как это режим добавления
    });

    dialogRef.afterClosed().subscribe((result) => {
      if (result) {
        this.tournamentsService.addTournament(result).subscribe(() => this.loadTournaments());
      }
    });
  }

  // Открыть попап для редактирования турнира
  editTournament(tournament: Tournament): void {
    const dialogRef = this.dialog.open(TournamentFormComponent, {
      width: '400px',
      data: tournament, // Передаём турнир для редактирования
    });

    dialogRef.afterClosed().subscribe((result) => {
      if (result) {
        result.id = tournament.id; // Добавляем ID для обновления
        this.tournamentsService.updateTournament(result).subscribe(() => this.loadTournaments());
      }
    });
  }

  // Удалить турнир
  deleteTournament(tournamentId: number): void {
    this.tournamentsService.deleteTournament(tournamentId).subscribe({
      next: () => this.loadTournaments(),
      error: (error) => console.error('Error deleting tournament:', error),
    });
  }
}