import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GameItemStatsFormComponent } from './game-item-stats-form.component';

describe('GameItemStatsFormComponent', () => {
  let component: GameItemStatsFormComponent;
  let fixture: ComponentFixture<GameItemStatsFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [GameItemStatsFormComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(GameItemStatsFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
