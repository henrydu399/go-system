import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminRolUsuarioComponent } from './admin-rol-usuario.component';

describe('AdminRolUsuarioComponent', () => {
  let component: AdminRolUsuarioComponent;
  let fixture: ComponentFixture<AdminRolUsuarioComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdminRolUsuarioComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdminRolUsuarioComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
