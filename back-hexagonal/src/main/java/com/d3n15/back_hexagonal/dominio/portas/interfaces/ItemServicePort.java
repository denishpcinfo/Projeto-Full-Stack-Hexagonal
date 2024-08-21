package com.d3n15.back_hexagonal.dominio.portas.interfaces;

import com.d3n15.back_hexagonal.dominio.dtos.QuantidadeDTO;
import com.d3n15.back_hexagonal.dominio.dtos.ItemDTO;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface ItemServicePort {

    ItemDTO buscarItem(Long id);

    Map<String, Object> buscarItens(int page, int size);

    ResponseEntity<Object> criarItem(ItemDTO itemDTO);

    void atualizarQuantidadeById(Long id, QuantidadeDTO quantidadeDTO);

    void deleteById(Long id);

}
