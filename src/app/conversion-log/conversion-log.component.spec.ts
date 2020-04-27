import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ConversionLogComponent } from './conversion-log.component';

describe('ConversionLogComponent', () => {
  let component: ConversionLogComponent;
  let fixture: ComponentFixture<ConversionLogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ConversionLogComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ConversionLogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
