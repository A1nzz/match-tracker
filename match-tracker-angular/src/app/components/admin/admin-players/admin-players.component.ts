import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { PlayersService } from '../../../services/players.service';
import { PlayerFormComponent } from '../../forms/player-form/player-form.component';
import { CommonModule } from '@angular/common';
import { Player } from '../../../models/models';

@Component({
  selector: 'app-admin-players',
  imports: [CommonModule],
  templateUrl: './admin-players.component.html',
  styleUrls: ['./admin-players.component.scss'],
})
export class AdminPlayersComponent implements OnInit {
  players: Player[] = [];

  constructor(
    private playersService: PlayersService,
    private dialog: MatDialog
  ) {}

  ngOnInit(): void {
    this.loadPlayers();
  }

  // Загрузить список игроков
  loadPlayers(): void {
    this.playersService.getPlayers().subscribe({
      next: (data) => (this.players = data),
      error: (error) => console.error('Error fetching players:', error),
    });
  }

  // Открыть попап для добавления игрока
  addPlayer(): void {
    const dialogRef = this.dialog.open(PlayerFormComponent, {
      data: null, // Передаём null, так как это режим добавления
    });

    dialogRef.afterClosed().subscribe((result) => {
      if (result) {
        this.playersService.addPlayer(result).subscribe(() => this.loadPlayers());
      }
    });
  }

  // Открыть попап для редактирования игрока
  editPlayer(player: Player): void {
    const dialogRef = this.dialog.open(PlayerFormComponent, {
      data: player, // Передаём игрока для редактирования
    });

    dialogRef.afterClosed().subscribe((result) => {
      if (result) {
        result.id = player.id; // Добавляем ID для обновления
        this.playersService.updatePlayer(result).subscribe(() => this.loadPlayers());
      }
    });
  }

  // Удалить игрока
  deletePlayer(playerId: number): void {
    this.playersService.deletePlayer(playerId).subscribe({
      next: () => this.loadPlayers(),
      error: (error) => console.error('Error deleting player:', error),
    });
  }
}