package com.d3n15.back_hexagonal.dominio;

import com.d3n15.back_hexagonal.dominio.dtos.ItemDTO;
import java.math.BigDecimal;

public class Item {

    private Long id;
    private String nome;
    private BigDecimal valorTotal;
    private Double quantidade;

    public Item() {
    }

    public Item(Long id, String nome, Double quantidade, BigDecimal valorTotal) {
        this.id = id;
        this.nome = nome;
        this.valorTotal = valorTotal;
        this.quantidade = quantidade;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public Double getQuantidade() {
        return quantidade;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setQuantidade(Double quantidade) {
        this.quantidade = quantidade;
    }

    public Item(ItemDTO itemDTO) {
        this.id = itemDTO.getId();
        this.nome = itemDTO.getNome();
        this.valorTotal = itemDTO.getValorTotal();
        this.quantidade = itemDTO.getQuantidade();
    }

    public void atualizarEstoque(double quantidade) {
        this.quantidade = quantidade;
    }

    public ItemDTO paraItemDTO() {
        return new ItemDTO(this.id, this.nome, this.valorTotal, this.quantidade);
    }

}
