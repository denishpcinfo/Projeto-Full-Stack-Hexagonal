package com.d3n15.back_hexagonal.aplicacao.adaptadores.controllers;

import com.d3n15.back_hexagonal.dominio.dtos.ItemDTO;
import com.d3n15.back_hexagonal.dominio.dtos.QuantidadeDTO;
import com.d3n15.back_hexagonal.dominio.portas.interfaces.ItemServicePort;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import java.util.Arrays;
import java.util.List;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.http.HttpStatus.OK;

@SpringBootTest
public class ItemControllerTest {

    @Mock
    private ItemServicePort itemServicePort;

    @InjectMocks
    private ItemController itemController;

    @Test
    void criarItensDeveChamarOMetodoCriarItemEretornarResponseEntityOk() {
        ItemDTO itemDTO = new ItemDTO(1L,"Produto A", null, 1.0);
        ResponseEntity<Object> response = itemController.criarItens(itemDTO);
        verify(itemServicePort, times(1)).criarItem(itemDTO);
        assertEquals(OK, response.getStatusCode());
    }

    @Test
    void getItemDeveRetornarListaDeItens() {
        List<ItemDTO> itensMock = Arrays.asList(new ItemDTO(1L,"Produto A", null, 1.0), new ItemDTO(2L,"Produto B", null, 2.0));
        when(itemServicePort.buscarItens()).thenReturn(itensMock);
        List<ItemDTO> result = itemController.getItem();
        assertEquals(itensMock, result);
    }

    @Test
    void getItensDeveRetornarItemPorId() {
        Long id = 1L;
        ItemDTO itemMock = new ItemDTO(1L,"Produto A", null, 1.0);
        when(itemServicePort.buscarItem(id)).thenReturn(itemMock);
        ItemDTO result = itemController.getItens(id);
        assertEquals(itemMock, result);
    }

    @Test
    void atualizarQuantidadeByIdDeveChamarOMetodoAtualizarQuantidadeById() {
        Long id = 1L;
        QuantidadeDTO quantidadeDTO = new QuantidadeDTO();
        quantidadeDTO.setQuantidade(5.0);
        itemController.atualizarQuantidadeById(id, quantidadeDTO);
        verify(itemServicePort, times(1)).atualizarQuantidadeById(id, quantidadeDTO);
    }

    @Test
    void deleteDeveChamarOMetodoDeleteById() {
        Long id = 1L;
        itemController.delete(id);
        verify(itemServicePort, times(1)).deleteById(id);
    }
}