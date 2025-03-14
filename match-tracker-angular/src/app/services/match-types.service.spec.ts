import { TestBed } from '@angular/core/testing';

import { MatchTypesService } from './match-types.service';

describe('MatchTypesService', () => {
  let service: MatchTypesService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(MatchTypesService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
