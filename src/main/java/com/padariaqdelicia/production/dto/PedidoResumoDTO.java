package com.padariaqdelicia.production.dto;

import com.padariaqdelicia.production.enums.StatusPedido;
import com.padariaqdelicia.production.domain.pedido.Pedido;
import java.math.BigDecimal;


public class PedidoResumoDTO {

    private Long id;
    private String clienteNome;
    private String clienteNumero;
    private StatusPedido status;
    private BigDecimal precoTotal;

    public PedidoResumoDTO(
        Long id,
        String clienteNome,
        String clienteNumero,
        StatusPedido status,
        BigDecimal precoTotal
    ) {
        this.id = id;
        this.clienteNome = clienteNome;
        this.clienteNumero = clienteNumero;
        this.status = status;
        this.precoTotal = precoTotal;
    }

    public Long getId() {
        return id;
    }

    public String getClienteNome() {
        return clienteNome;
    }

    public String getClienteNumero() {
        return clienteNumero;
    }

    public StatusPedido getStatus() {
        return status;
    }

    public BigDecimal getPrecoTotal() {
        return precoTotal;
    }

    //setters
    
    public void setPrecoTotal(BigDecimal precoTotal) {
        this.precoTotal = precoTotal;
    }

    public void setId(Long id) {
        this.id = id;
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

}
