import { Component, OnInit } from '@angular/core';
import { ItemsService } from '../../services/items.service';
import { Item } from '../../models/models';

@Component({
  selector: 'app-items',
  templateUrl: './items.component.html',
})
export class ItemsComponent implements OnInit {
  items: Item[] = [];

  constructor(private readonly itemsService: ItemsService) {}

  ngOnInit(): void {
    this.itemsService.getItems().subscribe({
      next: (data) => (this.items = data),
      error: (error) => console.error('Error fetching items:', error),
    });
  }
}