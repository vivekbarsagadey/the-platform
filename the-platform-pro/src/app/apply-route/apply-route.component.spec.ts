import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ApplyRouteComponent } from './apply-route.component';

describe('ApplyRouteComponent', () => {
  let component: ApplyRouteComponent;
  let fixture: ComponentFixture<ApplyRouteComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ApplyRouteComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ApplyRouteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
