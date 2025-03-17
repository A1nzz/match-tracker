import { TestBed } from '@angular/core/testing';

import { GameItemStatsService } from './game-item-stats.service';

describe('GameItemStatsService', () => {
  let service: GameItemStatsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(GameItemStatsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
