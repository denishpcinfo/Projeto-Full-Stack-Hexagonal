package com.d3n15.back_hexagonal.dominio.adaptadores.services;

import com.d3n15.back_hexagonal.dominio.Item;
import com.d3n15.back_hexagonal.dominio.dtos.QuantidadeDTO;
import com.d3n15.back_hexagonal.dominio.dtos.ItemDTO;
import com.d3n15.back_hexagonal.dominio.portas.interfaces.ItemServicePort;
import com.d3n15.back_hexagonal.dominio.portas.repositories.ItemRepositoryPort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.Sort;

import java.util.HashMap;
import java.util.Map;

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
    public Map<String, Object> buscarItens(int page, int size) {
        Map<String, Object> response = new HashMap<>();
        Page<Item> items = null;
        Pageable paging = PageRequest.of(page, size, Sort.Direction.ASC, "id");
        items = this.itemRepository.buscarTodos(paging);
        Page<ItemDTO> itemDTOS = items.map(Item::paraItemDTO);
        response.put("allItens", itemDTOS);
        response.put("currentPage", itemDTOS.getNumber());
        response.put("totalItens", itemDTOS.getTotalElements());
        response.put("totalPages", itemDTOS.getTotalPages());
        return response;
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
