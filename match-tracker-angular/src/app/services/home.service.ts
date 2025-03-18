import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, forkJoin } from 'rxjs';
import { HomePageDTO } from '../models/models';

@Injectable({
  providedIn: 'root',
})
export class HomeService {
  private apiUrl = 'http://localhost:8080/home';

  constructor(private http: HttpClient) {}

  // Метод для получения данных для главной страницы
  getHomePageData(): Observable<HomePageDTO> {
    return this.http.get<HomePageDTO>(this.apiUrl);
  }
}