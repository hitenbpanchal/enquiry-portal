import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NoramluserComponent } from './noramluser.component';

describe('NoramluserComponent', () => {
  let component: NoramluserComponent;
  let fixture: ComponentFixture<NoramluserComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [NoramluserComponent]
    });
    fixture = TestBed.createComponent(NoramluserComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
