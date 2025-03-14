import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Match } from '../models/models'

@Injectable({
  providedIn: 'root',
})
export class TournamentMatchesService {
  private apiUrl = 'http://localhost:8080/tournaments';

  constructor(private http: HttpClient) {}

  getMatches(tournamentId: number): Observable<Match[]> {
    return this.http.get<Match[]>(`${this.apiUrl}/${tournamentId}/matches`);
  }
}