import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ChangeslogComponent } from './changeslog.component';

describe('ChangeslogComponent', () => {
  let component: ChangeslogComponent;
  let fixture: ComponentFixture<ChangeslogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ChangeslogComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ChangeslogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
