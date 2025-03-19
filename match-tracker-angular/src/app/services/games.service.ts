import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Game } from '../models/models';

@Injectable({
  providedIn: 'root',
})
export class GamesService {
  private readonly apiUrl = 'http://localhost:8080/admin/games';

  constructor(private readonly http: HttpClient) {}

  // Получить все игры
  getGames(): Observable<Game[]> {
    return this.http.get<Game[]>(this.apiUrl);
  }

  // Добавить новую игру
  addGame(game: Game): Observable<Game> {
    return this.http.post<Game>(this.apiUrl, game);
  }

  // Обновить существующую игру
  updateGame(game: Game): Observable<Game> {
    return this.http.put<Game>(`${this.apiUrl}/${game.id}`, game);
  }

  // Удалить игру
  deleteGame(gameId: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${gameId}`);
  }
}