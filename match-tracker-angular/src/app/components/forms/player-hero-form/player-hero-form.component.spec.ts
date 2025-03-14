import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PlayerHeroFormComponent } from './player-hero-form.component';

describe('PlayerHeroFormComponent', () => {
  let component: PlayerHeroFormComponent;
  let fixture: ComponentFixture<PlayerHeroFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PlayerHeroFormComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PlayerHeroFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
