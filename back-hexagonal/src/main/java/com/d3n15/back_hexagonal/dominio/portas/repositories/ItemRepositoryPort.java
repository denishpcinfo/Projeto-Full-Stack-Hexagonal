package com.d3n15.back_hexagonal.dominio.portas.repositories;

import com.d3n15.back_hexagonal.dominio.Item;
import java.util.List;

public interface ItemRepositoryPort {
    List<Item> buscarTodos();

    Item buscarPeloId(Long id);

    void salvar(Item item);

    void deleteById(Long id);

}
