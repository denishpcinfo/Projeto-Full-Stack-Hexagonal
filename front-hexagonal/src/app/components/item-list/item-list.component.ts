import { Component, OnInit, ViewChild } from '@angular/core';
import { RouterLink } from '@angular/router';
import { Item } from '../../models/item';
import { ItemService } from '../../services/item.service';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-item-list',
  standalone: true,
  imports: [FormsModule, RouterLink, CommonModule],
  templateUrl: './item-list.component.html',
  styleUrl: './item-list.component.css'
})
export class ItemListComponent implements OnInit {

  items: Item[] = [];

  constructor(
    private itemService: ItemService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.loadItems();
  }

  loadItems(): void {
    this.itemService.getItems().subscribe((data) => {
      this.items = data;
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
}