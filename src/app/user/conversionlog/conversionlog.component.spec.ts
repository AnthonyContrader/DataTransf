import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ConversionlogComponent } from './conversionlog.component';

describe('ConversionlogComponent', () => {
  let component: ConversionlogComponent;
  let fixture: ComponentFixture<ConversionlogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ConversionlogComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ConversionlogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
