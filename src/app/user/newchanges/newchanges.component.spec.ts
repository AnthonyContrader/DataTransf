import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NewchangesComponent } from './newchanges.component';

describe('NewchangesComponent', () => {
  let component: NewchangesComponent;
  let fixture: ComponentFixture<NewchangesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NewchangesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NewchangesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
