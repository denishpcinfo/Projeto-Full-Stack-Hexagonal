package com.d3n15.back_hexagonal.infraestrutura.adaptadores.entidades;

import com.d3n15.back_hexagonal.dominio.Item;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "itens")
public class ItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private BigDecimal valorTotal;
    private Double quantidade;

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

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public void setQuantidade(Double quantidade) {
        this.quantidade = quantidade;
    }

    public ItemEntity() {
    }

    public ItemEntity(Item item) {
        this.nome = item.getNome();
        this.valorTotal = item.getValorTotal();
        this.quantidade = item.getQuantidade();
    }

    public void atualizar(Item item) {
        this.nome = item.getNome();
        this.valorTotal = item.getValorTotal();
        this.quantidade = item.getQuantidade();
    }

    public Item paraItemEntity() {
        return new Item(this.id, this.nome, this.quantidade, this.valorTotal);
    }
}
