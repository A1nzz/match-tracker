import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminGameStatsComponent } from './admin-game-stats.component';

describe('AdminGameStatsComponent', () => {
  let component: AdminGameStatsComponent;
  let fixture: ComponentFixture<AdminGameStatsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AdminGameStatsComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdminGameStatsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
