import { TestBed } from '@angular/core/testing';

import { ManipulaXmlService } from './manipula-xml.service';

describe('ManipulaXmlService', () => {
  let service: ManipulaXmlService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ManipulaXmlService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
