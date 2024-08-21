package com.d3n15.back_hexagonal.dominio.portas.interfaces;

import com.d3n15.back_hexagonal.dominio.dtos.ItemDTO;
import com.d3n15.back_hexagonal.dominio.dtos.QuantidadeDTO;
import com.d3n15.back_hexagonal.infraestrutura.adaptadores.repositories.ItemPortRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class ItemServicePortTest {

    @Mock
    private ItemServicePort itemServicePort;

    @Autowired
    private ItemServicePort itemService;

    @MockBean
    private ItemPortRepository itemPortRepository;

    @Test
    void criarItemDeveChamarOMetodoCriarItem() {
        ItemDTO itemDTO = new ItemDTO(1L,"Item 1", null, 1.0);
        itemServicePort.criarItem(itemDTO);
        verify(itemServicePort, times(1)).criarItem(itemDTO);
    }

    @Test
    void atualizarQuantidadeByIdDeveChamarOMetodoAtualizarQuantidadeById() {
        Long id = 1L;
        QuantidadeDTO quantidadeDTO = new QuantidadeDTO();
        quantidadeDTO.setQuantidade(5.0);
        itemServicePort.atualizarQuantidadeById(id, quantidadeDTO);
        verify(itemServicePort, times(1)).atualizarQuantidadeById(id, quantidadeDTO);
    }

    @Test
    void deleteByIdDeveChamarOMetodoDeleteById() {
        Long id = 1L;
        itemServicePort.deleteById(id);
        verify(itemServicePort, times(1)).deleteById(id);
    }

    @Test
    void testBuscarItens() {
        Map<String, Object> mockResponse = new HashMap<>();
        mockResponse.put("data", "some data");
        when(itemServicePort.buscarItens(0, 10)).thenReturn(mockResponse);
        Map<String, Object> result = itemServicePort.buscarItens(0, 10);
        assertNotNull(result);
        assertEquals(mockResponse, result);
    }

    @Test
    void testCriarItem() {
        ItemDTO itemDTO = new ItemDTO(1L,"Item 1", null, 1.0);
        ResponseEntity<Object> expectedResponse = new ResponseEntity<>("Item criado com sucesso", HttpStatus.CREATED);
        when(itemServicePort.criarItem(itemDTO)).thenReturn(expectedResponse);
        ResponseEntity<Object> result = itemServicePort.criarItem(itemDTO);
        assertNotNull(result);
        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertEquals("Item criado com sucesso", result.getBody());
    }

    @Test
    void testAtualizarQuantidadeById() {
        QuantidadeDTO quantidadeDTO = new QuantidadeDTO();
        quantidadeDTO.setQuantidade(5.0);
        doNothing().when(itemServicePort).atualizarQuantidadeById(1L, quantidadeDTO);
        itemServicePort.atualizarQuantidadeById(1L, quantidadeDTO);
        verify(itemServicePort, times(1)).atualizarQuantidadeById(1L, quantidadeDTO);
    }

    @Test
    void testDeleteById() {
        doNothing().when(itemServicePort).deleteById(1L);
        itemServicePort.deleteById(1L);
        verify(itemServicePort, times(1)).deleteById(1L);
    }

}
