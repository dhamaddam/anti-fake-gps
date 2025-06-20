import { WebPlugin } from '@capacitor/core';

import type { AntiFakeGpsPlugin } from './definitions';

export class AntiFakeGpsWeb extends WebPlugin implements AntiFakeGpsPlugin {
  async echo(options: { value: string }): Promise<{ value: string }> {
    console.log('ECHO', options);
    return options;
  }
  async getMock(options: {
    latitude: number;
    longitude: number;
    accuracy?: number;
    timestamp?: number;
  }): Promise<{ results: any[] }> {
    console.log('filter', options);
  
    // Simulasi deteksi lokasi palsu (misalnya jika longitude = 0, maka dianggap palsu)
    const isMock = options.longitude === 0;
  
    const mockResult = [
      {
        latitude: options.latitude,
        longitude: options.longitude,
        accuracy: options.accuracy ?? null,
        timestamp: options.timestamp ?? Date.now(),
        isMock: isMock
      }
    ];
  
    return {
      results: mockResult
    };
  }
  
}
