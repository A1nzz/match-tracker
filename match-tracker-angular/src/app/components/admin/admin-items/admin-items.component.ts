import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { ItemsService } from '../../../services/items.service';
import { ItemFormComponent } from '../../forms/item-form/item-form.component';
import { CommonModule } from '@angular/common';
import { Item } from '../../../models/models';

@Component({
  selector: 'app-admin-items',
  imports: [CommonModule],
  templateUrl: './admin-items.component.html',
  styleUrls: ['./admin-items.component.scss'],
})
export class AdminItemsComponent implements OnInit {
  items: Item[] = [];

  constructor(
    private readonly itemsService: ItemsService,
    private readonly dialog: MatDialog
  ) {}

  ngOnInit(): void {
    this.loadItems();
  }

  // Загрузить список предметов
  loadItems(): void {
    this.itemsService.getItems().subscribe({
      next: (data) => (this.items = data),
      error: (error) => console.error('Error fetching items:', error),
    });
  }

  // Открыть попап для добавления предмета
  addItem(): void {
    const dialogRef = this.dialog.open(ItemFormComponent, {
      data: null, // Передаём null, так как это режим добавления
    });

    dialogRef.afterClosed().subscribe((result) => {
      if (result) {
        this.itemsService.addItem(result).subscribe((newItem) => {
          this.items.push(newItem); // Добавляем новый предмет в локальный массив
        });
      }
    });
  }

  // Открыть попап для редактирования предмета
  editItem(item: Item): void {
    const dialogRef = this.dialog.open(ItemFormComponent, {
      data: item, // Передаём предмет для редактирования
    });

    dialogRef.afterClosed().subscribe((result) => {
      if (result) {
        result.id = item.id; // Добавляем ID для обновления
        this.itemsService.updateItem(result).subscribe((updatedItem) => {
          const index = this.items.findIndex(i => i.id === updatedItem.id);
          if (index !== -1) {
            this.items[index] = updatedItem; // Обновляем существующий предмет в локальном массиве
          }
        });
      }
    });
  }

  // Удалить предмет
  deleteItem(itemId: number): void {
    this.itemsService.deleteItem(itemId).subscribe({
      next: () => {
        this.items = this.items.filter(item => item.id !== itemId); // Удаляем предмет из локального массива
      },
      error: (error) => console.error('Error deleting item:', error),
    });
  }
}