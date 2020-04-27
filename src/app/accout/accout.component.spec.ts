import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AccoutComponent } from './accout.component';

describe('AccoutComponent', () => {
  let component: AccoutComponent;
  let fixture: ComponentFixture<AccoutComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AccoutComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AccoutComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
