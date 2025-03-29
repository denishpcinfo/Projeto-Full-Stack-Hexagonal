import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { ItemService } from './item.service';
import { Item } from '../models/item';

describe('ItemService', () => {
  let service: ItemService;
  let httpMock: HttpTestingController;

  const apiUrl = 'http://localhost:8080/api/itens';

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [ItemService]
    });

    service = TestBed.inject(ItemService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpMock.verify();
  });

  it('ao ser criado', () => {
    expect(service).toBeTruthy();
  });

  it('ao obter itens com parâmetros', () => {
    const mockResponse = {
      allItens: { content: [] },
      totalItens: 0
    };
  
    const params = { page: 1, size: 10 };
  
    service.getItens(params).subscribe((res) => {
      expect(res).toEqual(mockResponse);
    });
  
    const req = httpMock.expectOne(r => r.url === `${apiUrl}/todos-itens`);
    expect(req.request.method).toBe('GET');
    expect(req.request.params.get('page')).toBe('1');
    expect(req.request.params.get('size')).toBe('10');
    req.flush(mockResponse);
  });
  

  it('ao obter um único item por ID', () => {
    const item: Item = { id: 1, nome: 'Teste', valorTotal: 100, quantidade: 5 };

    service.getItem(1).subscribe((res) => {
      expect(res).toEqual(item);
    });

    const req = httpMock.expectOne(`${apiUrl}/1`);
    expect(req.request.method).toBe('GET');
    req.flush(item);
  });


  it('ao lidar com erro em getItens()', () => {
    const params = { page: 1, size: 10 };
  
    service.getItens(params).subscribe({
      next: () => fail('deveria ter falhado'),
      error: (err) => expect(err.status).toBe(500)
    });
  
    const req = httpMock.expectOne(req =>
      req.url === `${apiUrl}/todos-itens` &&
      req.params.get('page') === '1' &&
      req.params.get('size') === '10'
    );
  
    req.flush({ message: 'Erro no servidor' }, { status: 500, statusText: 'Server Error' });
  });
  


  it('ao criar um item', () => {
    const newItem: Item = { nome: 'Novo', valorTotal: 50, quantidade: 2 };
    const createdItem: Item = { id: 5, ...newItem };

    service.createItem(newItem).subscribe((res) => {
      expect(res).toEqual(createdItem);
    });

    const req = httpMock.expectOne(apiUrl);
    expect(req.request.method).toBe('POST');
    expect(req.request.body).toEqual(newItem);
    req.flush(createdItem);
  });


  it('ao atualizar um item por ID', () => {
    const item: Item = { id: 2, nome: 'Atualizado', valorTotal: 80, quantidade: 4 };
    service.updateItem(2, item).subscribe((res) => {
      expect(res).toEqual(item);
    });
    const req = httpMock.expectOne(`${apiUrl}/2`);
    expect(req.request.method).toBe('PUT');
    expect(req.request.body).toEqual(item);
    req.flush(item);
  });

  
  it('ao lidar com erro em updateItem()', () => {
    const item: Item = { id: 999, nome: 'Falha', valorTotal: 0, quantidade: 0 };
    service.updateItem(999, item).subscribe({
      next: () => fail('should have failed'),
      error: (err) => expect(err.status).toBe(500)
    });
    const req = httpMock.expectOne(`${apiUrl}/999`);
    req.flush({ message: 'Erro interno' }, { status: 500, statusText: 'Server Error' });
  });


  it('ao lidar com erro em createItem()', () => {
    const item: Item = { nome: 'Erro', valorTotal: 0, quantidade: 0 };
    service.createItem(item).subscribe({
      next: () => fail('should have failed'),
      error: (err) => expect(err.status).toBe(400)
    });
    const req = httpMock.expectOne(apiUrl);
    req.flush({ message: 'Erro de validação' }, { status: 400, statusText: 'Bad Request' });
  });


  it('ao lidar com erro em getItem()', () => {
    service.getItem(99).subscribe({
      next: () => fail('should have failed'),
      error: (err) => expect(err.status).toBe(404)
    });
    const req = httpMock.expectOne(`${apiUrl}/99`);
    req.flush({ message: 'Not found' }, { status: 404, statusText: 'Not Found' });
  });


  it('ao excluir um item pelo ID', () => {
    service.deleteItem(3).subscribe((res) => {
      expect(res).toBeNull();
    });
  
    const req = httpMock.expectOne(`${apiUrl}/3`);
    expect(req.request.method).toBe('DELETE');
    req.flush(null);
  });
  

  it('ao tratar erro ao excluir item (deleteItem)', () => {
    service.deleteItem(123).subscribe({
      next: () => fail('não deveria ter sucesso'),
      error: (err) => expect(err.status).toBe(500)
    });
  
    const req = httpMock.expectOne(`${apiUrl}/123`);
    expect(req.request.method).toBe('DELETE');
    req.flush({ message: 'Erro ao excluir' }, { status: 500, statusText: 'Erro interno' });
  });
  
});
