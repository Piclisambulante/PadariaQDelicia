package com.padariaqdelicia.production.controller;

import com.padariaqdelicia.production.domain.produto.Product;
import com.padariaqdelicia.production.service.produto.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/produtos")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public Product criarProduto(@RequestBody Product product) {
        return productService.criar(product);
    }

    @GetMapping("/{id}")
    public Product buscarPorId(@PathVariable Long id) {
        return productService.buscarPorId(id);
    }

    @GetMapping
    public List<Product> listarAtivos() {
        return productService.listarAtivos();
    }

    @PutMapping("/{id}")
    public Product atualizarProduto(
            @PathVariable Long id,
            @RequestBody Product produto
    ) {
        return productService.atualizarProduto(id, produto);
    }
    
    @DeleteMapping("/{id}")
    public void desativarProduto(@PathVariable Long id) {
        productService.desativarProduto(id);
    }
}
