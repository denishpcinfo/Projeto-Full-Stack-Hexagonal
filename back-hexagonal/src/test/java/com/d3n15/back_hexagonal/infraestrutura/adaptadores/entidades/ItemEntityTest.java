package com.d3n15.back_hexagonal.infraestrutura.adaptadores.entidades;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.d3n15.back_hexagonal.dominio.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest
class ItemEntityTest {

    @InjectMocks
    private ItemEntity itemEntity;

    @Mock
    private Item item;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        itemEntity = new ItemEntity();
    }

    @Test
    void testConstrutorComItem() {
        when(item.getNome()).thenReturn("Item Teste");
        when(item.getValorTotal()).thenReturn(BigDecimal.valueOf(100.00));
        when(item.getQuantidade()).thenReturn(2.0);
        itemEntity = new ItemEntity(item);
        assertEquals("Item Teste", itemEntity.getNome());
        assertEquals(BigDecimal.valueOf(100.00), itemEntity.getValorTotal());
        assertEquals(2.0, itemEntity.getQuantidade());
    }

    @Test
    void testAtualizar() {
        when(item.getNome()).thenReturn("Item Atualizado");
        when(item.getValorTotal()).thenReturn(BigDecimal.valueOf(150.00));
        when(item.getQuantidade()).thenReturn(3.0);
        itemEntity.atualizar(item);
        assertEquals("Item Atualizado", itemEntity.getNome());
        assertEquals(BigDecimal.valueOf(150.00), itemEntity.getValorTotal());
        assertEquals(3.0, itemEntity.getQuantidade());
    }

    @Test
    void testParaItemEntity() {
        itemEntity = new ItemEntity();
        itemEntity.setId(1L);
        itemEntity.setNome("Item para Entidade");
        itemEntity.setValorTotal(BigDecimal.valueOf(200.00));
        itemEntity.setQuantidade(4.0);
        Item result = itemEntity.paraItemEntity();
        assertEquals(1L, result.getId());
        assertEquals("Item para Entidade", result.getNome());
        assertEquals(BigDecimal.valueOf(200.00), result.getValorTotal());
        assertEquals(4.0, result.getQuantidade());
    }
}