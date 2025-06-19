import { WebPlugin } from '@capacitor/core';

import type { AntiFakeGpsPlugin } from './definitions';

export class AntiFakeGpsWeb extends WebPlugin implements AntiFakeGpsPlugin {
  async echo(options: { value: string }): Promise<{ value: string }> {
    console.log('ECHO', options);
    return options;
  }
}
