import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EdithUserComponent } from './edith-user.component';

describe('EdithUserComponent', () => {
  let component: EdithUserComponent;
  let fixture: ComponentFixture<EdithUserComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EdithUserComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EdithUserComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
