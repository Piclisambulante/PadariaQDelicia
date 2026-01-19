package com.padariaqdelicia.production.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.padariaqdelicia.production.domain.pedido.Pedido;
import com.padariaqdelicia.production.domain.pedido.StatusPedido;
import com.padariaqdelicia.production.repository.PedidoRepository;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final EmailService emailService;

    public PedidoService(PedidoRepository pedidoRepository, EmailService emailService) {
        this.pedidoRepository = pedidoRepository;
        this.emailService = emailService;
    }

    public Pedido buscarPorClienteNumero(String clienteNumero) {
        return pedidoRepository
                .findByClienteNumero(clienteNumero)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
    }

    public Pedido criarPedido(Pedido pedido) {
        pedido.setStatus(StatusPedido.PENDENTE);
        pedido.setTokenConfirmacao(UUID.randomUUID().toString());

        Pedido salvo = pedidoRepository.save(pedido);

        emailService.enviarConfirmacao(salvo);

        return salvo;
    }

    public void confirmarPedido(String token) {
        Pedido pedido = pedidoRepository
                .findByTokenConfirmacao(token)
                .orElseThrow(() -> new RuntimeException("Token inválido"));

        pedido.setStatus(StatusPedido.CONFIRMADO);
        pedidoRepository.save(pedido);
    }
}
