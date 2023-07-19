import { TestBed } from '@angular/core/testing';

import { EnqueryService } from './enquery.service';

describe('EnqueryService', () => {
  let service: EnqueryService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(EnqueryService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
