import { Routes } from '@angular/router';
import { ItemFormComponent } from './components/item-form/item-form.component';
import { ItemListComponent } from './components/item-list/item-list.component';

export const routes: Routes = [
  { path: 'itens', component: ItemListComponent },
  { path: 'itens/novo', component: ItemFormComponent },
  { path: 'itens/editar/:id', component: ItemFormComponent },
  { path: '', redirectTo: '/itens', pathMatch: 'full' },
];