import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, RouterModule } from '@angular/router';
import { TournamentMatchesService } from '../../services/tournament-matches.service';
import { Match } from '../../models/models';

@Component({
  selector: 'app-tournament-matches',
  imports: [RouterModule],
  templateUrl: './tournament-matches.component.html',
  styleUrls: ['./tournament-matches.component.scss'],
})
export class TournamentMatchesComponent implements OnInit {
  matches: Match[] = [];
  tournamentId: number | null = null;

  constructor(
    private route: ActivatedRoute,
    private tournamentMatchesService: TournamentMatchesService
  ) {}

  ngOnInit(): void {
    // Получаем tournamentId из параметров маршрута
    this.tournamentId = +this.route.snapshot.paramMap.get('tournamentId')!;

    if (this.tournamentId) {
      // Загружаем матчи для турнира
      this.tournamentMatchesService.getMatches(this.tournamentId).subscribe({
        next: (data) => (this.matches = data),
        error: (error) => console.error('Error fetching matches:', error),
      });
    }
  }
}