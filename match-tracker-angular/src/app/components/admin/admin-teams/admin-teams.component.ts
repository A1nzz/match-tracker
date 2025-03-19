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
    private readonly teamsService: TeamsService,
    private readonly dialog: MatDialog
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
      data: null,
    });

    dialogRef.afterClosed().subscribe((result) => {
      if (result) {
        this.teamsService.addTeam(result).subscribe((newTeam) => {
          this.teams.push(newTeam); 
        });
      }
    });
  }

  // Открыть попап для редактирования команды
  editTeam(team: Team): void {
    const dialogRef = this.dialog.open(TeamFormComponent, {
      data: team, 
    });

    dialogRef.afterClosed().subscribe((result) => {
      if (result) {
        result.id = team.id; 
        this.teamsService.updateTeam(result).subscribe((updatedTeam) => {
          const index = this.teams.findIndex(t => t.id === updatedTeam.id);
          if (index !== -1) {
            this.teams[index] = updatedTeam; 
          }
        });
      }
    });
  }

  // Удалить команду
  deleteTeam(teamId: number): void {
    this.teamsService.deleteTeam(teamId).subscribe({
      next: () => {
        this.teams = this.teams.filter(team => team.id !== teamId); 
      },
      error: (error) => console.error('Error deleting team:', error),
    });
  }
}