package com.d3n15.back_hexagonal.dominio.adaptadores.services;

import com.d3n15.back_hexagonal.dominio.Item;
import com.d3n15.back_hexagonal.dominio.dtos.QuantidadeDTO;
import com.d3n15.back_hexagonal.dominio.dtos.ItemDTO;
import com.d3n15.back_hexagonal.dominio.portas.interfaces.ItemServicePort;
import com.d3n15.back_hexagonal.dominio.portas.repositories.ItemRepositoryPort;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.stream.Collectors;

public class PedidoServiceImp implements ItemServicePort {

    private final ItemRepositoryPort itemRepository;

    public PedidoServiceImp(ItemRepositoryPort itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public ResponseEntity<Object> criarItem(ItemDTO itemDTO) {
        Item item = new Item(itemDTO);
        this.itemRepository.salvar(item);
        return ResponseEntity.ok().build();
    }

    @Override
    public ItemDTO buscarItem(Long id) {
        Item item = this.itemRepository.buscarPeloId(id);
        ItemDTO itemDTO = item.paraItemDTO();
        return itemDTO;
    }

    @Override
    public List<ItemDTO> buscarItens() {
        List<Item> items = this.itemRepository.buscarTodos();
        List<ItemDTO> itemDTOS = items.stream().map(Item::paraItemDTO).collect(Collectors.toList());
        return itemDTOS;
    }

    @Override
    public void atualizarQuantidadeById(Long id, QuantidadeDTO quantidadeDTO) {
        Item item = this.itemRepository.buscarPeloId(id);
        item.atualizarEstoque(quantidadeDTO.getQuantidade());
        this.itemRepository.salvar(item);
    }

    @Override
    public void deleteById(Long id) {
        this.itemRepository.deleteById(id);
    }
}
