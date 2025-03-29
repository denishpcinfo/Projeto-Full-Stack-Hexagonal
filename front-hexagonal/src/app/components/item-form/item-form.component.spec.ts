import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormsModule } from '@angular/forms';
import { RouterTestingModule } from '@angular/router/testing';
import { Router, ActivatedRoute } from '@angular/router';
import { ItemFormComponent } from './item-form.component';
import { ItemService } from '../../services/item.service';
import { MockItemService } from '../../services/mocks/mock-item.service';
import { MockActivatedRoute } from '../../services/mocks/mock-activated-route';

describe('ItemFormComponent', () => {
  let component: ItemFormComponent;
  let fixture: ComponentFixture<ItemFormComponent>;
  let mockRoute: MockActivatedRoute;
  let router: Router;

  beforeEach(async () => {
    mockRoute = new MockActivatedRoute();

    await TestBed.configureTestingModule({
      imports: [
        HttpClientTestingModule,
        FormsModule,
        RouterTestingModule,
        ItemFormComponent
      ],
      providers: [
        { provide: ItemService, useClass: MockItemService },
        { provide: ActivatedRoute, useValue: mockRoute }
      ]
    }).compileComponents();

    router = TestBed.inject(Router);
    fixture = TestBed.createComponent(ItemFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('deveria criar', () => {
    expect(component).toBeTruthy();
  });

  it('ao chamar createItem e navegar após enviar (novo item)', fakeAsync(() => {
    spyOn(router, 'navigate');

    component.item = {
      nome: 'Novo Item',
      valorTotal: 100,
      quantidade: 2
    };

    component.onSubmit();
    tick();

    expect(router.navigate).toHaveBeenCalledWith(['/itens']);
  }));

  it('ao chamar updateItem e navegar após enviar (editar item)', fakeAsync(() => {
    spyOn(router, 'navigate');

    component.item = {
      id: 99,
      nome: 'Atualizado',
      valorTotal: 150,
      quantidade: 4
    };

    component.onSubmit();
    tick();

    expect(router.navigate).toHaveBeenCalledWith(['/itens']);
  }));

  it('ao carregar o item quando o ID mudar via paramMap', fakeAsync(() => {
    mockRoute.setParamMap({ id: 1 });
    fixture.detectChanges();
    tick();

    expect(component.item.id).toBe(1);
    expect(component.item.nome).toBe('Item Mockado');
  }));
});
