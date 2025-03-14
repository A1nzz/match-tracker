import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, forkJoin } from 'rxjs';
import { map, switchMap } from 'rxjs/operators';
import { Team } from '../models/models';

@Injectable({
  providedIn: 'root',
})
export class TeamsService {
  private apiUrl = 'http://localhost:8080';
  private adminApiUrl = 'http://localhost:8080/admin/teams';

  constructor(private http: HttpClient) {}

  getTeams(): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/teams`).pipe(
      // Для каждой команды получаем игроков
      switchMap((teams) => {
        const teamsWithPlayers$ = teams.map((team) =>
          this.http.get<any[]>(`${this.apiUrl}/teams/${team.id}/players`).pipe(
            map((players) => ({ ...team, players }))
          )
        );
        // Объединяем все запросы в один поток
        return forkJoin(teamsWithPlayers$);
      })
    );
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