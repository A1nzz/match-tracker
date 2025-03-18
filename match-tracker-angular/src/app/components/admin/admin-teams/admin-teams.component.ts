import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { TeamsService } from '../../../services/teams.service';
import { TeamFormComponent } from '../../forms/team-form/team-form.component';
import { CommonModule } from '@angular/common';
import { Team } from '../../../models/models';

@Component({
  selector: 'app-admin-teams',
  imports: [CommonModule],
  templateUrl: './admin-teams.component.html',
  styleUrls: ['./admin-teams.component.scss'],
})
export class AdminTeamsComponent implements OnInit {
  teams: Team[] = [];

  constructor(
    private teamsService: TeamsService,
    private dialog: MatDialog
  ) {}

  ngOnInit(): void {
    this.loadTeams();
  }

  // Загрузить список команд
  loadTeams(): void {
    this.teamsService.getTeams().subscribe({
      next: (data) => (this.teams = data),
      error: (error) => console.error('Error fetching teams:', error),
    });
  }

  // Открыть попап для добавления команды
  addTeam(): void {
    const dialogRef = this.dialog.open(TeamFormComponent, {
      data: null, // Передаём null, так как это режим добавления
    });

    dialogRef.afterClosed().subscribe((result) => {
      if (result) {
        this.teamsService.addTeam(result).subscribe(() => this.loadTeams());
      }
    });
  }

  // Открыть попап для редактирования команды
  editTeam(team: Team): void {
    const dialogRef = this.dialog.open(TeamFormComponent, {
      data: team, // Передаём команду для редактирования
    });

    dialogRef.afterClosed().subscribe((result) => {
      if (result) {
        result.id = team.id; // Добавляем ID для обновления
        this.teamsService.updateTeam(result).subscribe(() => this.loadTeams());
      }
    });
  }

  // Удалить команду
  deleteTeam(teamId: number): void {
    this.teamsService.deleteTeam(teamId).subscribe({
      next: () => this.loadTeams(),
      error: (error) => console.error('Error deleting team:', error),
    });
  }
}