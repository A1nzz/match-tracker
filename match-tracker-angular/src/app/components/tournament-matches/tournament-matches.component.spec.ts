import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TournamentMatchesComponent } from './tournament-matches.component';

describe('TournamentMatchesComponent', () => {
  let component: TournamentMatchesComponent;
  let fixture: ComponentFixture<TournamentMatchesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TournamentMatchesComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TournamentMatchesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
