import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { MatchType } from '../models/models';


@Injectable({
  providedIn: 'root',
})
export class MatchTypesService {
  private apiUrl = 'http://localhost:8080/admin/match-types';

  constructor(private http: HttpClient) {}

  getMatchTypes(): Observable<MatchType[]> {
    return this.http.get<MatchType[]>(this.apiUrl);
  }

  addMatchType(matchType: MatchType): Observable<MatchType> {
    return this.http.post<MatchType>(this.apiUrl, matchType);
  }

  updateMatchType(matchType: MatchType): Observable<MatchType> {
    return this.http.put<MatchType>(`${this.apiUrl}/${matchType.id}`, matchType);
  }

  deleteMatchType(matchTypeId: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${matchTypeId}`);
  }
}