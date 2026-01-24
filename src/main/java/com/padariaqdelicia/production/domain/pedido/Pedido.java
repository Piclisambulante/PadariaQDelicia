package com.padariaqdelicia.production.domain.pedido;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.padariaqdelicia.production.domain.itemPedido.ItemPedido;
import com.padariaqdelicia.production.enums.StatusPedido;

import jakarta.persistence.*;

@Entity
@Table(name = "pedidos")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String clienteNome;
    private String clienteEmail;
    private String clienteNumero;

    private BigDecimal precoTotal;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusPedido status;

    @Column(unique = true)
    private String tokenConfirmacao;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<ItemPedido> itens;


    public Pedido() {}

    // getters e setters
    public Long getId() {
        return id;
    }

    public String getClienteEmail() {
        return clienteEmail;
    }

    public String getClienteNome() {
        return clienteNome;
    }

    public String getClienteNumero() {
        return clienteNumero;
    }

    public BigDecimal getPrecoTotal() {
        return precoTotal;
    }

    public StatusPedido getStatus() {
        return status;
    }

    public String getTokenConfirmacao() {
        return tokenConfirmacao;
    }

    public List<ItemPedido> getItens() {
        return itens;
    }

    public void setClienteEmail(String clienteEmail) {
        this.clienteEmail = clienteEmail;
    }

    public void setClienteNome(String clienteNome) {
        this.clienteNome = clienteNome;
    }

    public void setClienteNumero(String clienteNumero) {
        this.clienteNumero = clienteNumero;
    }

    public void setStatus(StatusPedido status) {
        this.status = status;
    }

    public void setPrecoTotal(BigDecimal precoTotal) {
        this.precoTotal = precoTotal;
    }

    public void setTokenConfirmacao(String tokenConfirmacao) {
        this.tokenConfirmacao = tokenConfirmacao;
    }
}
