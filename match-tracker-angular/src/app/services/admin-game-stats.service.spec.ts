import { TestBed } from '@angular/core/testing';

import { AdminGameStatsService } from './admin-game-stats.service';

describe('AdminGameStatsService', () => {
  let service: AdminGameStatsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AdminGameStatsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
