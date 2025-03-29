import { Item } from './item';

describe('Item', () => {
  it('ao criar uma instância', () => {
    expect(new Item()).toBeTruthy();
  });
});
