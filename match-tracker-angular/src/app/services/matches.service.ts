import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Match } from '../models/models'


@Injectable({
  providedIn: 'root',
})
export class MatchesService {
  private apiUrl = 'http://localhost:8080/admin/matches';

  constructor(private http: HttpClient) {}

  getMatches(): Observable<Match[]> {
    return this.http.get<Match[]>(this.apiUrl);
  }

  addMatch(match: Match): Observable<Match> {
    return this.http.post<Match>(this.apiUrl, match);
  }

  updateMatch(match: Match): Observable<Match> {
    return this.http.put<Match>(`${this.apiUrl}/${match.id}`, match);
  }

  deleteMatch(matchId: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${matchId}`);
  }
}