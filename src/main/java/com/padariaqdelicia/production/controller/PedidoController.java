package com.padariaqdelicia.production.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.padariaqdelicia.production.enums.StatusPedido;
import com.padariaqdelicia.production.service.pedido.PedidoService;
import com.padariaqdelicia.production.domain.pedido.Pedido;
import com.padariaqdelicia.production.dto.CriarPedidoDTO;
import com.padariaqdelicia.production.dto.PedidoResumoDTO;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/admin/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @GetMapping
    public List<PedidoResumoDTO> listar() {
        return pedidoService.buscarTodos();
    }

    @PostMapping("/{id}/status")
    public Pedido alterarStatus(
            @PathVariable Long id,
            @RequestParam StatusPedido status
    ) {
        return pedidoService.atualizarStatus(id, status);
    }
}



