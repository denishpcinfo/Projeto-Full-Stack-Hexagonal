import { of, throwError } from 'rxjs';
import { Item } from '../../models/item';

export class MockItemService {
  private shouldFail = {
    getItem: false,
    createItem: false,
    updateItem: false,
    deleteItem: false
  };

  private mockItem: Item = {
    id: 1,
    nome: 'Item Mockado',
    valorTotal: 99,
    quantidade: 3
  };

  // Métodos para simular falhas
  failOn(method: keyof typeof this.shouldFail) {
    this.shouldFail[method] = true;
  }

  resetFailures() {
    this.shouldFail = {
      getItem: false,
      createItem: false,
      updateItem: false,
      deleteItem: false
    };
  }

  getItem(id: number) {
    if (this.shouldFail.getItem) {
      return throwError(() => new Error('Erro ao buscar item'));
    }
    return of(this.mockItem);
  }

  createItem(item: Item) {
    if (this.shouldFail.createItem) {
      return throwError(() => new Error('Erro ao criar item'));
    }
    return of({ ...item, id: Math.floor(Math.random() * 1000) });
  }

  updateItem(id: number, item: Item) {
    if (this.shouldFail.updateItem) {
      return throwError(() => new Error('Erro ao atualizar item'));
    }
    return of({ ...item });
  }

  deleteItem(id: number) {
    if (this.shouldFail.deleteItem) {
      return throwError(() => new Error('Erro ao excluir item'));
    }
    return of(void 0);
  }

  getItens(params: any) {
    return of({
      allItens: { content: [this.mockItem] },
      totalItens: 1
    });
  }
  
}
