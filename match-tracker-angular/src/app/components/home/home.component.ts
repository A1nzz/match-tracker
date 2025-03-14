import { Component, OnInit } from '@angular/core';
import { HomeService } from '../../services/home.service';
import { RouterModule } from '@angular/router';
import { Tournament, Team, Hero } from '../../models/models';

@Component({
  selector: 'app-home',
  imports: [RouterModule],
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss'],
})
export class HomeComponent implements OnInit {
  tournaments: Tournament[] = [];
  teams: Team[] = [];
  heroes: Hero[] = [];

  constructor(private homeService: HomeService) {}

  ngOnInit(): void {
    // Получаем все данные
    this.homeService.getAllData().subscribe({
      next: ([tournaments, teams, heroes]) => {
        this.tournaments = tournaments;
        this.teams = teams;
        this.heroes = heroes;
      },
      error: (error) => console.error('Error fetching data:', error),
    });
  }
}