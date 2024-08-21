package com.d3n15.back_hexagonal.infraestrutura.adaptadores.repositories;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.d3n15.back_hexagonal.dominio.Item;
import com.d3n15.back_hexagonal.infraestrutura.adaptadores.entidades.ItemEntity;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

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
        Pageable pageable = PageRequest.of(0, 10);
        ItemEntity itemEntity1 = new ItemEntity();
        itemEntity1.setId(1L);
        itemEntity1.setNome("Item 1");
        ItemEntity itemEntity2 = new ItemEntity();
        itemEntity2.setId(2L);
        itemEntity2.setNome("Item 2");
        List<ItemEntity> itemEntities = Arrays.asList(itemEntity1, itemEntity2);
        Page<ItemEntity> itemEntityPage = new PageImpl<>(itemEntities);
        when(itemPortRepository.findAll(pageable)).thenReturn(itemEntityPage);
        Page<Item> itemsPage = itemRepository.buscarTodos(pageable);
        assertNotNull(itemsPage);
        assertEquals(2, itemsPage.getTotalElements());
        assertEquals("Item 1", itemsPage.getContent().get(0).getNome());
        assertEquals("Item 2", itemsPage.getContent().get(1).getNome());
        verify(itemPortRepository, times(1)).findAll(pageable);
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
