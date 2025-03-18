import { Component, OnInit } from '@angular/core';
import { RouterModule } from '@angular/router';
import { TournamentsService } from '../../services/tournaments.service';
import { Tournament } from '../../models/models';

@Component({
  selector: 'app-tournaments',
  templateUrl: './tournaments.component.html',
  imports: [RouterModule],
})
export class TournamentsComponent implements OnInit {
  tournaments: Tournament[] = [];

  constructor(private tournamentsService: TournamentsService) {}

  ngOnInit(): void {
    this.tournamentsService.getTournaments().subscribe({
      next: (data) => (this.tournaments = data),
      error: (error) => console.error('Error fetching tournaments:', error),
    });
  }
}