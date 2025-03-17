import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminGameItemStatsComponent } from './admin-game-item-stats.component';

describe('AdminGameItemStatsComponent', () => {
  let component: AdminGameItemStatsComponent;
  let fixture: ComponentFixture<AdminGameItemStatsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AdminGameItemStatsComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdminGameItemStatsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
