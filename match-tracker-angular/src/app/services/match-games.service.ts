import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Game } from '../models/models';

@Injectable({
  providedIn: 'root',
})
export class MatchGamesService {
  private apiUrl = 'http://localhost:8080/matches';

  constructor(private http: HttpClient) {}

  getGames(matchId: number): Observable<Game[]> {
    return this.http.get<Game[]>(`${this.apiUrl}/${matchId}/games`);
  }
}