package com.d3n15.back_hexagonal.dominio.dtos;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ItemDTOTest {

    @Test
    void testCriacaoDeItemDTO() {
        Long id = 1L;
        String nome = "Produto A";
        BigDecimal valorTotal = new BigDecimal("100.00");
        Double quantidade = 2.0;
        ItemDTO itemDTO = new ItemDTO(id, nome, valorTotal, quantidade);
        assertEquals(id, itemDTO.getId());
        assertEquals(nome, itemDTO.getNome());
        assertEquals(valorTotal, itemDTO.getValorTotal());
        assertEquals(quantidade, itemDTO.getQuantidade());
    }
}