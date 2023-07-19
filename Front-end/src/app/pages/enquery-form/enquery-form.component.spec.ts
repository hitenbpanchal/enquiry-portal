import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EnqueryFormComponent } from './enquery-form.component';

describe('EnqueryFormComponent', () => {
  let component: EnqueryFormComponent;
  let fixture: ComponentFixture<EnqueryFormComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [EnqueryFormComponent]
    });
    fixture = TestBed.createComponent(EnqueryFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
