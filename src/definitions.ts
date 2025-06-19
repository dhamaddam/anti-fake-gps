export interface AntiFakeGpsPlugin {
  echo(options: { value: string }): Promise<{ value: string }>;
}
