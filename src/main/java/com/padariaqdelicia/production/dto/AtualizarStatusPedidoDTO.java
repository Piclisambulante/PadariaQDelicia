package com.padariaqdelicia.production.dto;

import com.padariaqdelicia.production.enums.StatusPedido;

public class AtualizarStatusPedidoDTO {

    private StatusPedido status;

    public StatusPedido getStatus() {
        return status;
    }

    public void setStatus(StatusPedido status) {
        this.status = status;
    }
}