package com.d3n15.back_hexagonal.aplicacao.adaptadores.controllers;

import com.d3n15.back_hexagonal.dominio.dtos.QuantidadeDTO;
import com.d3n15.back_hexagonal.dominio.dtos.ItemDTO;
import com.d3n15.back_hexagonal.dominio.portas.interfaces.ItemServicePort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/itens")
public class ItemController {

    private final ItemServicePort itemServicePort;

    public ItemController(ItemServicePort itemServicePort) {
        this.itemServicePort = itemServicePort;
    }

    @PostMapping
    ResponseEntity<Object> criarItens(@RequestBody ItemDTO itemDTO) {
        itemServicePort.criarItem(itemDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    List<ItemDTO> getItem() {
        return itemServicePort.buscarItens();
    }

    @GetMapping(value = "/{id}")
    ItemDTO getItens(@PathVariable Long id){
        return itemServicePort.buscarItem(id);
    }

    @PutMapping(value = "/{id}")
    void atualizarQuantidadeById(@PathVariable Long id, @RequestBody QuantidadeDTO quantidadeDTO) {
        itemServicePort.atualizarQuantidadeById(id, quantidadeDTO);
    }

    @DeleteMapping(value = "/{id}")
    void delete(@PathVariable Long id) {
        itemServicePort.deleteById(id);
    }
}
