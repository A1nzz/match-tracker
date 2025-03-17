import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GameStatFormComponent } from './game-stat-form.component';

describe('GameStatFormComponent', () => {
  let component: GameStatFormComponent;
  let fixture: ComponentFixture<GameStatFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [GameStatFormComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(GameStatFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
