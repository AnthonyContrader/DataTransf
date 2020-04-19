import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NewConversionComponent } from './new-conversion.component';

describe('NewConversionComponent', () => {
  let component: NewConversionComponent;
  let fixture: ComponentFixture<NewConversionComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NewConversionComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NewConversionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
