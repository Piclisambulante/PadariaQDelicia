package com.padariaqdelicia.production.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.padariaqdelicia.production.domain.produto.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findByName(String name);

}
