package com.d3n15.back_hexagonal.dominio.dtos;

import java.math.BigDecimal;

public class ItemDTO {
    private Long id;
    private String nome;
    private BigDecimal valorTotal;
    private Double quantidade;

    public ItemDTO(Long id, String nome, BigDecimal valorTotal, Double quantidade) {
        this.id = id;
        this.nome = nome;
        this.valorTotal = valorTotal;
        this.quantidade = quantidade;
    }

    public Long getId() { return id; }

    public String getNome() {
        return nome;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public Double getQuantidade() {
        return quantidade;
    }
}
