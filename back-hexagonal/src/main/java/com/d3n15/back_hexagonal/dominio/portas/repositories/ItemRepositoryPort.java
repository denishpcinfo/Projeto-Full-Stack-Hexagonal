package com.d3n15.back_hexagonal.dominio.portas.repositories;

import com.d3n15.back_hexagonal.dominio.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ItemRepositoryPort {

    Page<Item> buscarTodos(Pageable pageable);

    Item buscarPeloId(Long id);

    void salvar(Item item);

    void deleteById(Long id);

}
