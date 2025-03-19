import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { GameStat } from '../models/models';



@Injectable({
  providedIn: 'root',
})
export class GameStatsService {
  private readonly apiUrl = 'http://localhost:8080/games';

  constructor(private readonly http: HttpClient) {}

  getGameStats(gameId: number): Observable<GameStat[]> {
    return this.http.get<GameStat[]>(`${this.apiUrl}/${gameId}/stats`);
  }
}