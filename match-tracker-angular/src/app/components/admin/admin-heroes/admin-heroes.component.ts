import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { HeroesService } from '../../../services/heroes.service';
import { HeroFormComponent } from '../../forms/hero-form/hero-form.component';
import { CommonModule } from '@angular/common';
import { Hero } from '../../../models/models';

@Component({
  selector: 'app-admin-heroes',
  imports: [CommonModule],
  templateUrl: './admin-heroes.component.html',
  styleUrls: ['./admin-heroes.component.scss'],
})
export class AdminHeroesComponent implements OnInit {
  heroes: Hero[] = [];

  constructor(
    private heroesService: HeroesService,
    private dialog: MatDialog
  ) {}

  ngOnInit(): void {
    this.loadHeroes();
  }

  // Загрузить список героев
  loadHeroes(): void {
    this.heroesService.getHeroes().subscribe({
      next: (data) => (this.heroes = data),
      error: (error) => console.error('Error fetching heroes:', error),
    });
  }

  // Открыть попап для добавления героя
  addHero(): void {
    const dialogRef = this.dialog.open(HeroFormComponent, {
      width: '400px',
      data: null, // Передаём null, так как это режим добавления
    });

    dialogRef.afterClosed().subscribe((result) => {
      if (result) {
        this.heroesService.addHero(result).subscribe(() => this.loadHeroes());
      }
    });
  }

  // Открыть попап для редактирования героя
  editHero(hero: Hero): void {
    const dialogRef = this.dialog.open(HeroFormComponent, {
      width: '400px',
      data: hero, // Передаём героя для редактирования
    });

    dialogRef.afterClosed().subscribe((result) => {
      if (result) {
        result.id = hero.id; // Добавляем ID для обновления
        this.heroesService.updateHero(result).subscribe(() => this.loadHeroes());
      }
    });
  }

  // Удалить героя
  deleteHero(heroId: number): void {
    this.heroesService.deleteHero(heroId).subscribe({
      next: () => this.loadHeroes(),
      error: (error) => console.error('Error deleting hero:', error),
    });
  }
}