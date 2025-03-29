import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { Router } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';

import { ItemListComponent } from './item-list.component';
import { ItemService } from '../../services/item.service';
import { MockItemService } from '../../services/mocks/mock-item.service';
import { Item } from '../../models/item';

describe('ItemListComponent', () => {
  let fixture: ComponentFixture<ItemListComponent>;
  let component: ItemListComponent;
  let router: Router;
  let mockService: MockItemService;

  beforeEach(async () => {
    mockService = new MockItemService();

    await TestBed.configureTestingModule({
      imports: [
        HttpClientTestingModule,
        RouterTestingModule,
        ItemListComponent
      ],
      providers: [
        { provide: ItemService, useValue: mockService }
      ]
    }).compileComponents();

    router = TestBed.inject(Router);
    fixture = TestBed.createComponent(ItemListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges(); // ngOnInit -> loadItems()
  });

  it('deveria criar', () => {
    expect(component).toBeTruthy();
  });

  it('ao carregar itens no init', () => {
    expect(component.itens.length).toBeGreaterThan(0);
    expect(component.count).toBe(1);
  });

  it('ao navegar para editar a página em editItem()', () => {
    const spy = spyOn(router, 'navigate');
    const item: Item = { id: 123, nome: 'Teste', valorTotal: 10, quantidade: 2 };

    component.editItem(item);

    expect(spy).toHaveBeenCalledWith(['/itens/editar', 123]);
  });

  it('ao chamar deleteItem e recarregar a lista', () => {
    spyOn(component, 'loadItems').and.callThrough();
    spyOn(window, 'alert');

    component.deleteItem({ id: 1, nome: '', valorTotal: 0, quantidade: 0 });

    expect(component.loadItems).toHaveBeenCalled();
    expect(window.alert).toHaveBeenCalledWith('Deletado com sucesso!');
  });

  it('ao definir os parâmetros corretamente em getRequestParams()', () => {
    component.getRequestParams(2, 5);

    expect(component.params['page']).toBe(1); // 2 - 1
    expect(component.params['size']).toBe(5);
  });

  it('ao atualizar a página e recarregar os itens na mudança de página', () => {
    spyOn(component, 'loadItems');
    component.handlePageChange(3);

    expect(component.page).toBe(3);
    expect(component.loadItems).toHaveBeenCalled();
  });
});
