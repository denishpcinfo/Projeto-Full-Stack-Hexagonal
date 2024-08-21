package com.d3n15.back_hexagonal.dominio.dtos;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class QuantidadeDTOTest {

    @Test
    void getQuantidadeDeveRetornarValorCorreto() {
        double quantidade = 5.0;
        QuantidadeDTO quantidadeDTO = new QuantidadeDTO();
        quantidadeDTO.setQuantidade(quantidade);
        assertEquals(quantidade, quantidadeDTO.getQuantidade());
    }

    @Test
    void setQuantidadeDeveAlterarValorCorretamente() {
        double quantidadeInicial = 3.0;
        double quantidadeAlterada = 7.0;
        QuantidadeDTO quantidadeDTO = new QuantidadeDTO();
        quantidadeDTO.setQuantidade(quantidadeInicial);
        assertEquals(quantidadeInicial, quantidadeDTO.getQuantidade());
        quantidadeDTO.setQuantidade(quantidadeAlterada);
        assertEquals(quantidadeAlterada, quantidadeDTO.getQuantidade());
    }
}