package com.padariaqdelicia.production.repository;

import java.util.Optional;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.padariaqdelicia.production.domain.pedido.Pedido;
import com.padariaqdelicia.production.domain.pedido.StatusPedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    
    Optional<Pedido> findByClienteNumero(String clienteNumero);

    List<Pedido> findByClienteNome(String clienteNome);

    List<Pedido> findByStatus(StatusPedido status);
}
