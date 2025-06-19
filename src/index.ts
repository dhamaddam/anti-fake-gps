import { registerPlugin } from '@capacitor/core';

import type { AntiFakeGpsPlugin } from './definitions';

const AntiFakeGps = registerPlugin<AntiFakeGpsPlugin>('AntiFakeGps', {
  web: () => import('./web').then((m) => new m.AntiFakeGpsWeb()),
});

export * from './definitions';
export { AntiFakeGps };
