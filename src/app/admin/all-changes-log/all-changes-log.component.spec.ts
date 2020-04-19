import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AllChangesLogComponent } from './all-changes-log.component';

describe('AllChangesLogComponent', () => {
  let component: AllChangesLogComponent;
  let fixture: ComponentFixture<AllChangesLogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AllChangesLogComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AllChangesLogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
