import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Item } from '../models/models';


@Injectable({
  providedIn: 'root',
})
export class ItemsService {
  private readonly apiUrl = 'http://localhost:8080/items';
  private readonly adminApiUrl = 'http://localhost:8080/admin/items';

  constructor(private readonly http: HttpClient) {}

  getItems(): Observable<Item[]> {
    return this.http.get<Item[]>(this.apiUrl);
  }

  addItem(item: Item): Observable<Item> {
    return this.http.post<Item>(this.adminApiUrl, item);
  }

  updateItem(item: Item): Observable<Item> {
    return this.http.put<Item>(`${this.adminApiUrl}/${item.id}`, item);
  }

  deleteItem(itemId: number): Observable<void> {
    return this.http.delete<void>(`${this.adminApiUrl}/${itemId}`);
  }
}