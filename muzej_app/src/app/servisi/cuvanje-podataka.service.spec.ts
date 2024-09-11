import { TestBed } from '@angular/core/testing';

import { CuvanjePodatakaService } from './cuvanje-podataka.service';

describe('CuvanjePodatakaService', () => {
  let service: CuvanjePodatakaService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CuvanjePodatakaService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
