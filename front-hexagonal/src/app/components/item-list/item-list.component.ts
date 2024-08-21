import { Component, OnInit} from '@angular/core';
import { RouterLink } from '@angular/router';
import { Item } from '../../models/item';
import { ItemService } from '../../services/item.service';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { NgxPaginationModule } from 'ngx-pagination';

@Component({
  selector: 'app-item-list',
  standalone: true,
  imports: [FormsModule, RouterLink, CommonModule, NgxPaginationModule],
  templateUrl: './item-list.component.html',
  styleUrl: './item-list.component.css'
})
export class ItemListComponent implements OnInit {

  itens: Item[] = [];
  page = 1;
  count = 0;
  pageSize = 10;
  params: any = {};

  constructor(
    private itemService: ItemService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.loadItems();
  }

  loadItems(): void {
    this.getRequestParams(this.page, this.pageSize);
    this.itemService.getItens(this.params).subscribe({
      next: (data) => {
      const { allItens, totalItens } = data;
      this.itens = allItens.content;
      this.count = totalItens;
      }
    });
  }

  editItem(itemEdit: Item): void {
    this.router.navigate(['/itens/editar', itemEdit.id]);
  }

  deleteItem(item: Item ): void {
    this.itemService.deleteItem(item.id as number).subscribe(() => {
      alert('Deletado com sucesso!');
      this.loadItems();
    });
  }

  getRequestParams(page: number, pageSize: number): any {
    this.params[`page`] = 0;
    if (page) {
      this.params[`page`] = page - 1;
    }

    if (pageSize) {
      this.params[`size`] = pageSize;
    }
    
  }

  handlePageChange(event: number): void {
    this.page = event;
    this.loadItems();
  }
}