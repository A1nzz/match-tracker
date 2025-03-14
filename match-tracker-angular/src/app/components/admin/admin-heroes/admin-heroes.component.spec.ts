import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminHeroesComponent } from './admin-heroes.component';

describe('AdminHeroesComponent', () => {
  let component: AdminHeroesComponent;
  let fixture: ComponentFixture<AdminHeroesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AdminHeroesComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdminHeroesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
