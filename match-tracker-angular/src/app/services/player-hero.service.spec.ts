import { TestBed } from '@angular/core/testing';

import { PlayerHeroService } from './player-hero.service';

describe('PlayerHeroService', () => {
  let service: PlayerHeroService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PlayerHeroService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
