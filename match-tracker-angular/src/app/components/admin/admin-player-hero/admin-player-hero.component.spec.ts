import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminPlayerHeroComponent } from './admin-player-hero.component';

describe('AdminPlayerHeroComponent', () => {
  let component: AdminPlayerHeroComponent;
  let fixture: ComponentFixture<AdminPlayerHeroComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AdminPlayerHeroComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdminPlayerHeroComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
