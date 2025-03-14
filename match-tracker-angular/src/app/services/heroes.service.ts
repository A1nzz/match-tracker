import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Hero } from '../models/models';


@Injectable({
  providedIn: 'root',
})
export class HeroesService {
  private apiUrl = 'http://localhost:8080/heroes';
  private adminApiUrl = "http://localhost:8080/admin/heroes";

  constructor(private http: HttpClient) {}

  getHeroes(): Observable<Hero[]> {
    return this.http.get<Hero[]>(this.apiUrl);
  }

  addHero(hero: Hero): Observable<Hero> {
    return this.http.post<Hero>(this.adminApiUrl, hero);
  }

  updateHero(hero: Hero): Observable<Hero> {
    return this.http.put<Hero>(`${this.adminApiUrl}/${hero.id}`, hero);
  }

  deleteHero(heroId: number): Observable<void> {
    return this.http.delete<void>(`${this.adminApiUrl}/${heroId}`);
  }
}