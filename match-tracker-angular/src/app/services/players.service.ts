import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Player } from '../models/models';

@Injectable({
  providedIn: 'root',
})
export class PlayersService {
  private apiUrl = 'http://localhost:8080/admin/players';

  constructor(private http: HttpClient) {}

  // Получить всех игроков
  getPlayers(): Observable<Player[]> {
    return this.http.get<Player[]>(this.apiUrl);
  }

  // Добавить нового игрока
  addPlayer(player: Player): Observable<Player> {
    return this.http.post<Player>(this.apiUrl, player);
  }

  // Обновить существующего игрока
  updatePlayer(player: Player): Observable<Player> {
    return this.http.put<Player>(`${this.apiUrl}/${player.id}`, player);
  }

  // Удалить игрока
  deletePlayer(playerId: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${playerId}`);
  }
}