import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AllConversionLogComponent } from './all-conversion-log.component';

describe('AllConversionLogComponent', () => {
  let component: AllConversionLogComponent;
  let fixture: ComponentFixture<AllConversionLogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AllConversionLogComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AllConversionLogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
