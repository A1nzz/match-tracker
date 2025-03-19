import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Team } from '../models/models';

@Injectable({
  providedIn: 'root',
})
export class TeamsService {
  private readonly apiUrl = 'http://localhost:8080/teams';
  private readonly adminApiUrl = 'http://localhost:8080/admin/teams';

  constructor(private readonly http: HttpClient) {}

  getTeams(): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}`)
  }

  addTeam(team: Team): Observable<Team> {
    return this.http.post<Team>(this.adminApiUrl, team);
  }

  updateTeam(team: Team): Observable<Team> {
    return this.http.put<Team>(`${this.adminApiUrl}/${team.id}`, team);
  }

  deleteTeam(teamId: number): Observable<void> {
    return this.http.delete<void>(`${this.adminApiUrl}/${teamId}`);
  }
}