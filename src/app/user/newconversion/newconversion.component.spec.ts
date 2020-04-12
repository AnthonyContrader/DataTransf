import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NewconversionComponent } from './newconversion.component';

describe('NewconversionComponent', () => {
  let component: NewconversionComponent;
  let fixture: ComponentFixture<NewconversionComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NewconversionComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NewconversionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
