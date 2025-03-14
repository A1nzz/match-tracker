import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { PlayerHero } from '../models/models';


@Injectable({
  providedIn: 'root',
})
export class PlayerHeroService {
  private apiUrl = 'http://localhost:8080/admin/player-heroes';

  constructor(private http: HttpClient) {}

  // Получить все связи между игроками и героями
  getPlayerHeroes(): Observable<PlayerHero[]> {
    return this.http.get<PlayerHero[]>(this.apiUrl);
  }

  // Добавить новую связь
  addPlayerHero(playerHero: PlayerHero): Observable<PlayerHero> {
    return this.http.post<PlayerHero>(this.apiUrl, playerHero);
  }

  // Обновить существующую связь
  updatePlayerHero(playerHero: PlayerHero): Observable<PlayerHero> {
    return this.http.put<PlayerHero>(`${this.apiUrl}/${playerHero.id}`, playerHero);
  }

  // Удалить связь
  deletePlayerHero(playerHeroId: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${playerHeroId}`);
  }
}