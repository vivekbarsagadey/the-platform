import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ApplyBulkComponent } from './apply-bulk.component';

describe('ApplyBulkComponent', () => {
  let component: ApplyBulkComponent;
  let fixture: ComponentFixture<ApplyBulkComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ApplyBulkComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ApplyBulkComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
