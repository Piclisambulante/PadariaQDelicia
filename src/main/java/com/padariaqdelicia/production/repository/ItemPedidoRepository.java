package com.padariaqdelicia.production.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.padariaqdelicia.production.domain.itemPedido.ItemPedido;

public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Long> {
    Optional <ItemPedido> findById(Long id);

    List<ItemPedido> findByPedidoId(Long pedidoId);

    List<ItemPedido> findByQuantidade(int quantidade);

    List<ItemPedido> findByPrecoUnitario(double precoUnitario);
}