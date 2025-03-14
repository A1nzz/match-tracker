import { TestBed } from '@angular/core/testing';

import { MatchGamesService } from './match-games.service';

describe('MatchGamesService', () => {
  let service: MatchGamesService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(MatchGamesService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
