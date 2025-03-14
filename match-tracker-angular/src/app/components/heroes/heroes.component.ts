import { Component, OnInit } from '@angular/core';
import { HeroesService } from '../../services/heroes.service';
import { CommonModule } from '@angular/common';
import { Hero } from '../../models/models';


@Component({
  selector: 'app-heroes',
  imports: [CommonModule],
  templateUrl: './heroes.component.html',
  styleUrls: ['./heroes.component.scss'],
})
export class HeroesComponent implements OnInit {
  heroes: Hero[] = [];

  constructor(private heroesService: HeroesService) {}

  ngOnInit(): void {
    this.heroesService.getHeroes().subscribe({
      next: (data) => (this.heroes = data),
      error: (error) => console.error('Error fetching heroes:', error),
    });
  }
}