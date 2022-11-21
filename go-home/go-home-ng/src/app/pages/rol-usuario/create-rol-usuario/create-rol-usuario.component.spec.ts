import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateRolUsuarioComponent } from './create-rol-usuario.component';

describe('CreateRolUsuarioComponent', () => {
  let component: CreateRolUsuarioComponent;
  let fixture: ComponentFixture<CreateRolUsuarioComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CreateRolUsuarioComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CreateRolUsuarioComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
