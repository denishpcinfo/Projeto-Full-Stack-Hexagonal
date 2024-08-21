package com.d3n15.back_hexagonal.dominio.portas.interfaces;

import com.d3n15.back_hexagonal.dominio.dtos.ItemDTO;
import com.d3n15.back_hexagonal.dominio.dtos.QuantidadeDTO;
import com.d3n15.back_hexagonal.infraestrutura.adaptadores.repositories.ItemPortRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class ItemServicePortTest {

    @Mock
    private ItemServicePort itemServicePort;

    @Autowired
    private ItemServicePort itemService;

    @MockBean
    private ItemPortRepository itemPortRepository;

    @Test
    void buscarItensDeveRetornarListaDeItemDTOs() {
        List<ItemDTO> itensMock = Arrays.asList(new ItemDTO(1L,"Item 1", null, 1.0), new ItemDTO(2L,"Item 2", null, 2.0));
        when(itemServicePort.buscarItens()).thenReturn(itensMock);
        List<ItemDTO> result = itemServicePort.buscarItens();
        assertEquals(itensMock, result);
    }

    @Test
    void buscarItensDeveRetornarListaDeItensDTO() {
        ItemDTO item1 = new ItemDTO(1L, "Produto A", new BigDecimal("100.00"), 2.0);
        ItemDTO item2 = new ItemDTO(2L, "Produto B", new BigDecimal("200.00"), 3.0);
        List<ItemDTO> itemsMock = Arrays.asList(item1, item2);
        when(itemServicePort.buscarItens()).thenReturn(itemsMock);
        List<ItemDTO> result = itemServicePort.buscarItens();
        assertEquals(itemsMock, result);
    }

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
}