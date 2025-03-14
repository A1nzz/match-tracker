import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminTournamentsComponent } from './admin-tournaments.component';

describe('AdminTournamentsComponent', () => {
  let component: AdminTournamentsComponent;
  let fixture: ComponentFixture<AdminTournamentsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AdminTournamentsComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdminTournamentsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
