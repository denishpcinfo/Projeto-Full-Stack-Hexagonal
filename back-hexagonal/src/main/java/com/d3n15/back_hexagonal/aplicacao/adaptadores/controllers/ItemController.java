package com.d3n15.back_hexagonal.aplicacao.adaptadores.controllers;

import com.d3n15.back_hexagonal.dominio.dtos.QuantidadeDTO;
import com.d3n15.back_hexagonal.dominio.dtos.ItemDTO;
import com.d3n15.back_hexagonal.dominio.portas.interfaces.ItemServicePort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

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

    @GetMapping("/todos-itens")
    ResponseEntity<Map<String, Object>> getItens(
            @RequestParam(name = "page", required = false) int page,
            @RequestParam("size") int size) {
    return new ResponseEntity<>(itemServicePort.buscarItens(page, size), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    ItemDTO getItem(@PathVariable Long id){
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
