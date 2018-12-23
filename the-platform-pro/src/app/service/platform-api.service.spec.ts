import { TestBed, inject } from '@angular/core/testing';

import { PlatformApiService } from './platform-api.service';

describe('PlatformApiService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [PlatformApiService]
    });
  });

  it('should be created', inject([PlatformApiService], (service: PlatformApiService) => {
    expect(service).toBeTruthy();
  }));
});
