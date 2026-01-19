package com.padariaqdelicia.production.domain.pedido;

import java.math.BigDecimal;

import jakarta.persistence.*;

@Entity
@Table(name = "pedidos")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String clienteNome;

    @Column(nullable = false)
    private String clienteNumero;

    @Column(nullable = false)
    private BigDecimal precoTotal;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusPedido status;

    @Column(nullable = false, unique = true)
    private String tokenConfirmacao;

    protected Pedido() {}

    public Long getId() {
        return id;
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

    public void setClienteNome(String clienteNome) {
        this.clienteNome = clienteNome;
    }

    public void setClienteNumero(String clienteNumero) {
        this.clienteNumero = clienteNumero;
    }

    public void setPrecoTotal(BigDecimal precoTotal) {
        this.precoTotal = precoTotal;
    }

    public void setStatus(StatusPedido status) {
        this.status = status;
    }

    public void setTokenConfirmacao(String tokenConfirmacao) {
        this.tokenConfirmacao = tokenConfirmacao;
    }
}
