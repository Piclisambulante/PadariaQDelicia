package com.padariaqdelicia.production.service.pedido;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;


import java.util.List;
import java.util.UUID;

import com.padariaqdelicia.production.service.email.EmailService;
import com.padariaqdelicia.production.domain.itemPedido.ItemPedido;
import com.padariaqdelicia.production.domain.pedido.Pedido;
import com.padariaqdelicia.production.domain.produto.Product;
import com.padariaqdelicia.production.dto.ItemPedidoDTO;
import com.padariaqdelicia.production.dto.PedidoResumoDTO;
import com.padariaqdelicia.production.enums.StatusPedido;
import com.padariaqdelicia.production.repository.ItemPedidoRepository;
import com.padariaqdelicia.production.repository.PedidoRepository;
import com.padariaqdelicia.production.repository.ProductRepository;

import jakarta.transaction.Transactional;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ProductRepository productRepository;
    private final ItemPedidoRepository itemPedidoRepository;
    private final EmailService emailService;

    public PedidoService(
    PedidoRepository pedidoRepository,
    ProductRepository productRepository,
    ItemPedidoRepository itemPedidoRepository,
    EmailService emailService
) {
    this.pedidoRepository = pedidoRepository;
    this.productRepository = productRepository;
    this.itemPedidoRepository = itemPedidoRepository;
    this.emailService = emailService;
}


    public List<PedidoResumoDTO> buscarTodos() {
    return pedidoRepository.findAll().stream()
        .map(p -> new PedidoResumoDTO(
            p.getId(),
            p.getClienteNome(),
            p.getClienteNumero(),
            p.getStatus(),
            p.getPrecoTotal()
        ))
        .toList();
    }

    public Pedido findBy(Long id) {
        return pedidoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
    }

    public List<Pedido> findByClienteNome(String clienteNome) {
        return pedidoRepository.findByClienteNome(clienteNome);
    }

    public Pedido findByClienteNumero(String clienteNumero) {
        return pedidoRepository.findByClienteNumero(clienteNumero)
            .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
    }

    public List<Pedido> findByStatus(StatusPedido status) {
        return pedidoRepository.findByStatus(status);
    }

    public Pedido atualizarStatus(Long pedidoId, StatusPedido novoStatus) {
        Pedido pedido = findBy(pedidoId);
        validarTransicao(pedido.getStatus(), novoStatus);
        pedido.setStatus(novoStatus);
        return pedidoRepository.save(pedido);
    }

    private void validarTransicao(StatusPedido atual, StatusPedido novo) {
        if (atual == StatusPedido.ENTREGUE || atual == StatusPedido.CANCELADO) {
            throw new RuntimeException("Pedido finalizado não pode ser alterado");
        }
    }

    public Pedido confirmarPedido(String token) {
        Pedido pedido = pedidoRepository
            .findByTokenConfirmacao(token)
            .orElseThrow(() -> new RuntimeException("Token inválido"));

        if (pedido.getStatus() != StatusPedido.AGUARDANDO_CONFIRMACAO) {
            throw new RuntimeException("Pedido já confirmado ou cancelado");
        }

        pedido.setStatus(StatusPedido.CONFIRMADO);
        return pedidoRepository.save(pedido);
    }

    @Transactional
    public Pedido criarPedido(
        String clienteNome,
        String clienteEmail,
        String clienteNumero,
        List<ItemPedidoDTO> itens
    ) {
        Pedido pedido = new Pedido();
        pedido.setClienteNome(clienteNome);
        pedido.setClienteEmail(clienteEmail);
        pedido.setClienteNumero(clienteNumero);
        pedido.setStatus(StatusPedido.AGUARDANDO_CONFIRMACAO);
        pedido.setTokenConfirmacao(UUID.randomUUID().toString());
        pedido.setPrecoTotal(BigDecimal.ZERO);

        pedido = pedidoRepository.save(pedido);

        BigDecimal total = BigDecimal.ZERO;

        for (ItemPedidoDTO itemDTO : itens) {

            Product produto = productRepository.findById(itemDTO.getProductId())
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

            BigDecimal preco = BigDecimal.valueOf(produto.getPrice());

            ItemPedido item = new ItemPedido();
            item.setPedido(pedido);
            item.setProduto(produto);
            item.setQuantidade(itemDTO.getQuantidade());
            item.setPrecoUnitario(preco);

            BigDecimal subtotal = preco.multiply(
                BigDecimal.valueOf(itemDTO.getQuantidade())
            );

            total = total.add(subtotal);

            itemPedidoRepository.save(item);
        }
        emailService.enviarConfirmacao(pedido);



        pedido.setPrecoTotal(total);
        return pedidoRepository.save(pedido);
    }
}
