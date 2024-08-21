package com.d3n15.back_hexagonal.infraestrutura.adaptadores.repositories;

import com.d3n15.back_hexagonal.dominio.Item;
import com.d3n15.back_hexagonal.dominio.portas.repositories.ItemRepositoryPort;
import com.d3n15.back_hexagonal.infraestrutura.adaptadores.entidades.ItemEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;

@Component
public class ItemRepository implements ItemRepositoryPort {

    private final ItemPortRepository itemPortRepository;

    public ItemRepository(ItemPortRepository itemPortRepository) {
        this.itemPortRepository = itemPortRepository;
    }

    @Override
    public Page<Item> buscarTodos(Pageable pageable) {
        Page<ItemEntity> itemEntities = this.itemPortRepository.findAll(pageable);
        return itemEntities.map(ItemEntity::paraItemEntity);
    }

    @Override
    public Item buscarPeloId(Long id) {
        Optional<ItemEntity> itemEntity = this.itemPortRepository.findById(id);

        if (itemEntity.isPresent()){
            return itemEntity.get().paraItemEntity();
        }else{
            throw new RuntimeException("Item não existe!");
        }
    }

    @Override
    public void salvar(Item item) {
        ItemEntity itemEntity;
        if (Objects.isNull(item.getId())){
            itemEntity = new ItemEntity(item);
        }
        else {
            itemEntity = this.itemPortRepository.findById(item.getId()).get();
            itemEntity.atualizar(item);
        }
        this.itemPortRepository.save(itemEntity);
    }

    @Override
    public void deleteById(Long id) {
        Optional<ItemEntity> itemEntity = this.itemPortRepository.findById(id);
        if (itemEntity.isPresent()){
            this.itemPortRepository.deleteById(id);
        }else{
            throw new RuntimeException("Item não existe!");
        }
    }

}
