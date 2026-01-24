package com.padariaqdelicia.production.controller;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.padariaqdelicia.production.service.pedido.PedidoService;


@RestController
@RequestMapping("/pedidos")
public class ConfirmarPedido {

    private final PedidoService pedidoService;

    public ConfirmarPedido(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @GetMapping("/confirmar")
    public String confirmar(@RequestParam String token) {
        pedidoService.confirmarPedido(token);
        return "Pedido confirmado com sucesso! ✅";
    }
}
