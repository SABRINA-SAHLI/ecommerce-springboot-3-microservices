package com.ecommerce.productservice.service;


import com.ecommerce.productservice.model.Product;
import com.ecommerce.productservice.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ProductService {

    Logger LOGGER = LoggerFactory.getLogger(ProductService.class);
    @Autowired
    ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product addProduct(Product product) {
        LOGGER.info("adding product...");
        return productRepository.save(product);
    }

    public List<Product> findAll(){
        return productRepository.findAll();
    }

    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public Product updateProduct(Long id, Product product) {
        if (productRepository.existsById(id)) {
           product.setId(id);
            return productRepository.save(product);
        } else {
            throw new RuntimeException("Product not found");
        }
    }
}
