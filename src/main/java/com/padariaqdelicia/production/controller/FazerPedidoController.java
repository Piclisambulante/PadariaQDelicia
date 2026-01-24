package com.padariaqdelicia.production.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.padariaqdelicia.production.domain.pedido.Pedido;
import com.padariaqdelicia.production.dto.CriarPedidoDTO;
import com.padariaqdelicia.production.service.pedido.PedidoService;

@RestController
@RequestMapping("/fazer-pedido")
public class FazerPedidoController {

    private final PedidoService pedidoService;

    public FazerPedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping
    public Pedido criar(@RequestBody CriarPedidoDTO dto) {
        return pedidoService.criarPedido(
            dto.getClienteNome(),
            dto.getClienteEmail(),
            dto.getClienteNumero(),
            dto.getItens()
        );
    }
}
