package com.padariaqdelicia.production.service.produto;

import com.padariaqdelicia.production.domain.produto.Product;
import com.padariaqdelicia.production.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product criar(Product product) {
        return productRepository.save(product);
    }

    public List<Product> listarAtivos() {
        return productRepository.findByAtivoTrue();
    }

    public Product buscarPorId(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
    }

    public Product atualizarProduto(Long id, Product updatedProduct) {
        Product product = buscarPorId(id);

        product.setName(updatedProduct.getName());
        product.setPrice(updatedProduct.getPrice());
        product.setAtivo(updatedProduct.isAtivo());

        return productRepository.save(product);
    }

    public void desativarProduto(Long id) {
        Product product = buscarPorId(id);
        product.setAtivo(false);
        productRepository.save(product);
    }
}
