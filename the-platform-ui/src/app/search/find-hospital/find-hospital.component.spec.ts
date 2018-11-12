import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FindHospitalComponent } from './find-hospital.component';

describe('FindHospitalComponent', () => {
  let component: FindHospitalComponent;
  let fixture: ComponentFixture<FindHospitalComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FindHospitalComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FindHospitalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
