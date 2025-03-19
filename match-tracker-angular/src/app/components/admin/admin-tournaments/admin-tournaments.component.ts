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
    private readonly tournamentsService: TournamentsService,
    private readonly dialog: MatDialog
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

  // Форматирование даты в нужный формат
  formatDate(dateString: string): string {
    const date = new Date(dateString);
    return date.toISOString().split('T')[0]; // Формат YYYY-MM-DD
  }

  // Открыть попап для добавления турнира
  addTournament(): void {
    const dialogRef = this.dialog.open(TournamentFormComponent, {
      data: null, // Передаём null, так как это режим добавления
    });

    dialogRef.afterClosed().subscribe((result) => {
      if (result) {
        this.tournamentsService.addTournament(result).subscribe((newTournament) => {
          this.tournaments.push(newTournament); // Добавляем новый турнир в список
        });
      }
    });
  }

  // Открыть попап для редактирования турнира
  editTournament(tournament: Tournament): void {
    const dialogRef = this.dialog.open(TournamentFormComponent, {
      data: tournament, // Передаём турнир для редактирования
    });

    dialogRef.afterClosed().subscribe((result) => {
      if (result) {
        result.id = tournament.id; // Добавляем ID для обновления
        this.tournamentsService.updateTournament(result).subscribe((updatedTournament) => {
          const index = this.tournaments.findIndex(t => t.id === updatedTournament.id);
          if (index !== -1) {
            this.tournaments[index] = updatedTournament; // Обновляем турнир в списке
          }
        });
      }
    });
  }

  // Удалить турнир
  deleteTournament(tournamentId: number): void {
    this.tournamentsService.deleteTournament(tournamentId).subscribe({
      next: () => {
        this.tournaments = this.tournaments.filter(tournament => tournament.id !== tournamentId); // Удаляем турнир из списка
      },
      error: (error) => console.error('Error deleting tournament:', error),
    });
  }
}