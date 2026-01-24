package com.padariaqdelicia.production.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import com.padariaqdelicia.production.domain.produto.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findByName(String name);

    List<Product> findByAtivoTrue();

    Optional<Product> findById(Long id);

}
