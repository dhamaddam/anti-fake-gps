# anti-fake-gps

anti fake gps

## Install

```bash
npm install anti-fake-gps
npx cap sync
```

## API

<docgen-index>

* [`echo(...)`](#echo)
* [`getMock(...)`](#getmock)

</docgen-index>

<docgen-api>
<!--Update the source file JSDoc comments and rerun docgen to update the docs below-->

### echo(...)

```typescript
echo(options: { value: string; }) => Promise<{ value: string; }>
```

| Param         | Type                            |
| ------------- | ------------------------------- |
| **`options`** | <code>{ value: string; }</code> |

**Returns:** <code>Promise&lt;{ value: string; }&gt;</code>

--------------------


### getMock(...)

```typescript
getMock(options: { latitude: number; longitude: number; accuracy?: number; timestamp?: number; }) => Promise<{ results: any[]; }>
```

| Param         | Type                                                                                         |
| ------------- | -------------------------------------------------------------------------------------------- |
| **`options`** | <code>{ latitude: number; longitude: number; accuracy?: number; timestamp?: number; }</code> |

**Returns:** <code>Promise&lt;{ results: any[]; }&gt;</code>

--------------------

</docgen-api>
