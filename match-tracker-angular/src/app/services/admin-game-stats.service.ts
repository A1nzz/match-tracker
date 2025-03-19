import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { GameStats } from '../models/models';

@Injectable({
  providedIn: 'root',
})
export class AdminGameStatsService {
  private readonly apiUrl = 'http://localhost:8080/admin/game-stats';

  constructor(private readonly http: HttpClient) {}

  // Получить всю игровую статистику
  getGameStats(): Observable<GameStats[]> {
    return this.http.get<GameStats[]>(this.apiUrl);
  }

  // Добавить новую игровую статистику
  addGameStat(gameStat: GameStats): Observable<GameStats> {
    return this.http.post<GameStats>(this.apiUrl, gameStat);
  }

  // Обновить существующую игровую статистику
  updateGameStat(gameStat: GameStats): Observable<GameStats> {
    return this.http.put<GameStats>(`${this.apiUrl}/${gameStat.id}`, gameStat);
  }

  // Удалить игровую статистику
  deleteGameStat(gameStatId: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${gameStatId}`);
  }
}