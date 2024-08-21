import { Component, OnInit } from '@angular/core';
import { ItemService } from '../../services/item.service';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { Item } from '../../models/item';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-item-form',
  standalone: true,
  imports: [FormsModule, RouterLink],
  templateUrl: './item-form.component.html',
  styleUrl: './item-form.component.css'
})
export class ItemFormComponent implements OnInit {

  item: Item = new Item();

  constructor(
    private itemService: ItemService,
    private router: Router,
    private route: ActivatedRoute
  ) { }

  ngOnInit(): void {
    const id = this.route.snapshot.params['id'];
    if (id) {
      this.itemService.getItem(id).subscribe((data) => {
        this.item = data;
      });
    }
  }

  onSubmit(): void {
    if (this.item.id) {
      this.itemService.updateItem(this.item.id, this.item).subscribe(() => {
        alert('Salvo com sucesso!');
        this.router.navigate(['/itens']);
      });
    } else {
      this.itemService.createItem(this.item).subscribe(() => {
        alert('Salvo com sucesso!');
        this.router.navigate(['/itens']);
      });
    }
  }
}
