import { Component, OnInit } from '@angular/core';
import { TeamsService } from '../../services/teams.service';
import { Team } from '../../models/models';

@Component({
  selector: 'app-teams',
  templateUrl: './teams.component.html',
})
export class TeamsComponent implements OnInit {
  teams: Team[] = [];

  constructor(private readonly teamsService: TeamsService) {}

  ngOnInit(): void {
    this.teamsService.getTeams().subscribe({
      next: (data) => (this.teams = data),
      error: (error) => console.error('Error fetching teams:', error),
    });
  }
}