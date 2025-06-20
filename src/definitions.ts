export interface AntiFakeGpsPlugin {
  echo(options: { value: string }): Promise<{ value: string }>;
  getMock(options: {
    latitude: number;
    longitude: number;
    accuracy?: number;
    timestamp?: number;
  }): Promise<{results:any[]}>;
}
