package com.d3n15.back_hexagonal.infraestrutura.adaptadores.repositories;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.d3n15.back_hexagonal.dominio.Item;
import com.d3n15.back_hexagonal.infraestrutura.adaptadores.entidades.ItemEntity;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class ItemRepositoryTest {

    @Mock
    private ItemPortRepository itemPortRepository;

    @InjectMocks
    private ItemRepository itemRepository;

    @Test
    void testBuscarTodos() {
        ItemEntity itemEntity1 = new ItemEntity();
        ItemEntity itemEntity2 = new ItemEntity();
        List<ItemEntity> itemEntitiesMock = Arrays.asList(itemEntity1, itemEntity2);
        when(itemPortRepository.findAll()).thenReturn(itemEntitiesMock);
        List<Item> result = itemRepository.buscarTodos();
        assertEquals(2, result.size());
        verify(itemPortRepository, times(1)).findAll();
    }

    @Test
    void testBuscarPeloIdExistente() {
        Long id = 1L;
        ItemEntity itemEntityMock = new ItemEntity();
        when(itemPortRepository.findById(id)).thenReturn(Optional.of(itemEntityMock));
        Item result = itemRepository.buscarPeloId(id);
        assertNotNull(result);
        verify(itemPortRepository, times(1)).findById(id);
    }

    @Test
    void testBuscarPeloIdNaoExistente() {
        Long id = 1L;
        when(itemPortRepository.findById(id)).thenReturn(Optional.empty());
        RuntimeException exception = assertThrows(RuntimeException.class, () -> itemRepository.buscarPeloId(id));
        assertEquals("Item não existe!", exception.getMessage());
        verify(itemPortRepository, times(1)).findById(id);
    }

    @Test
    void testSalvarNovoItem() {
        Item item = new Item();
        item.setId(null);
        ItemEntity itemEntityMock = new ItemEntity(item);
        when(itemPortRepository.save(any(ItemEntity.class))).thenReturn(itemEntityMock);
        itemRepository.salvar(item);
        verify(itemPortRepository, times(1)).save(any(ItemEntity.class));
    }

    @Test
    void testSalvarItemExistente() {
        Long id = 1L;
        Item item = new Item();
        item.setId(id);
        ItemEntity itemEntityMock = new ItemEntity();
        when(itemPortRepository.findById(id)).thenReturn(Optional.of(itemEntityMock));
        itemRepository.salvar(item);
        verify(itemPortRepository, times(1)).findById(id);
        verify(itemPortRepository, times(1)).save(itemEntityMock);
    }

    @Test
    void testDeleteByIdExistente() {
        Long id = 1L;
        ItemEntity itemEntityMock = new ItemEntity();
        when(itemPortRepository.findById(id)).thenReturn(Optional.of(itemEntityMock));
        itemRepository.deleteById(id);
        verify(itemPortRepository, times(1)).deleteById(id);
    }

    @Test
    void testDeleteByIdNaoExistente() {
        Long id = 1L;
        when(itemPortRepository.findById(id)).thenReturn(Optional.empty());
        RuntimeException exception = assertThrows(RuntimeException.class, () -> itemRepository.deleteById(id));
        assertEquals("Item não existe!", exception.getMessage());
        verify(itemPortRepository, times(1)).findById(id);
    }
}