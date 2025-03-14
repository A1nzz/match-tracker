import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MatchGamesComponent } from './match-games.component';

describe('MatchGamesComponent', () => {
  let component: MatchGamesComponent;
  let fixture: ComponentFixture<MatchGamesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [MatchGamesComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MatchGamesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
