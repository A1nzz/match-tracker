import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Tournament } from '../models/models'



@Injectable({
  providedIn: 'root',
})
export class TournamentsService {
  private readonly apiUrl = 'http://localhost:8080/tournaments';
  private readonly adminApiUrl = 'http://localhost:8080/admin/tournaments';

  constructor(private readonly http: HttpClient) {}

  getTournaments(): Observable<Tournament[]> {
    return this.http.get<Tournament[]>(this.apiUrl);
  }

  addTournament(tournament: Tournament): Observable<Tournament> {
    return this.http.post<Tournament>(this.adminApiUrl, tournament);
  }

  updateTournament(tournament: Tournament): Observable<Tournament> {
    return this.http.put<Tournament>(`${this.adminApiUrl}/${tournament.id}`, tournament);
  }

  deleteTournament(tournamentId: number): Observable<void> {
    return this.http.delete<void>(`${this.adminApiUrl}/${tournamentId}`);
  }
}