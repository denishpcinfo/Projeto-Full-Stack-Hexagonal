package com.d3n15.back_hexagonal.dominio.adaptadores.services;

import com.d3n15.back_hexagonal.dominio.Item;
import com.d3n15.back_hexagonal.dominio.dtos.ItemDTO;
import com.d3n15.back_hexagonal.dominio.dtos.QuantidadeDTO;
import com.d3n15.back_hexagonal.infraestrutura.adaptadores.repositories.ItemRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
class PedidoServiceImpTest {

    @Mock
    private ItemRepository itemRepository;

    @InjectMocks
    private PedidoServiceImp pedidoServiceImp;

    @Test
    void criarItem() {
        ItemDTO itemDTO = new ItemDTO(1L,"item 1", BigDecimal.valueOf(10.00), 1.5);
        Item item = new Item(itemDTO);
        pedidoServiceImp.criarItem(itemDTO);
        verify(itemRepository, times(1)).salvar(Mockito.any(Item.class));
    }

    @Test
    void atualizarQuantidadeById() {
        Long id = 1L;
        QuantidadeDTO quantidadeDTO = new QuantidadeDTO();
        quantidadeDTO.setQuantidade(5);
        Item item = new Item();
        item.setId(id);
        item.setQuantidade(10.00);
        when(itemRepository.buscarPeloId(id)).thenReturn(item);
        pedidoServiceImp.atualizarQuantidadeById(id, quantidadeDTO);
        verify(itemRepository, times(1)).buscarPeloId(id);
        verify(itemRepository, times(1)).salvar(item);
        assertEquals(5, item.getQuantidade());
    }

    @Test
    void deleteById() {
        Long id = 1L;
        pedidoServiceImp.deleteById(id);
        verify(itemRepository, times(1)).deleteById(id);
    }
}