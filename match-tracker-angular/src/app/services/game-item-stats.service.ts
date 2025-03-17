import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { GameItemStats } from '../models/models';


@Injectable({
  providedIn: 'root',
})
export class GameItemStatsService {
  private apiUrl = 'http://localhost:8080/admin/game-item-stats';

  constructor(private http: HttpClient) {}

  getGameItemStats(): Observable<GameItemStats[]> {
    return this.http.get<GameItemStats[]>(this.apiUrl);
  }

  addGameItemStat(gameItemStat: GameItemStats): Observable<GameItemStats> {
    return this.http.post<GameItemStats>(this.apiUrl, gameItemStat);
  }

  updateGameItemStat(gameItemStat: GameItemStats): Observable<GameItemStats> {
    return this.http.put<GameItemStats>(`${this.apiUrl}/${gameItemStat.id}`, gameItemStat);
  }

  deleteGameItemStat(gameItemStatId: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${gameItemStatId}`);
  }
}