package com.padariaqdelicia.production.domain.itemPedido;

import jakarta.persistence.*;
import java.math.BigDecimal;

import com.padariaqdelicia.production.domain.produto.Product;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.padariaqdelicia.production.domain.pedido.Pedido;

@Entity
@Table(name = "itens_pedido")
public class ItemPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    private Product produto;

    @ManyToOne
    @JoinColumn(name = "pedido_id")
    @JsonBackReference
    private Pedido pedido;


    private Integer quantidade;

    private BigDecimal precoUnitario;

    public ItemPedido() {}

    // getters e setters
    public Long getId() {
        return id;
    }

    public Product getProduto() {
        return produto;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public BigDecimal getPrecoUnitario() {
        return precoUnitario;
    }

    public void setProduto(Product produto) {
        this.produto = produto;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public void setPrecoUnitario(BigDecimal precoUnitario) {
        this.precoUnitario = precoUnitario;
    }
}
