package com.ecommerce.productservice.controller;


import com.ecommerce.productservice.client.CategoryClient;
import com.ecommerce.productservice.model.Product;
import com.ecommerce.productservice.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private static final Logger LOGGER
            = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryClient categoryClient;


    public ProductController(ProductService productService, CategoryClient categoryClient) {
        this.productService = productService;
        this.categoryClient = categoryClient;
    }

    @GetMapping
    public List<Product> getAll() {
        LOGGER.info(" fetching all products");
        return productService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getById(@PathVariable Long id) {
        LOGGER.info("Fetching product when id={}", id);
        Optional<Product> product = productService.findById(id);
        return product.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    @PostMapping
    public ResponseEntity<Product> add(@RequestBody Product product){
        LOGGER.info("Product add: {}", product);
        Product createdProduct = productService.addProduct(product);
        LOGGER.info("Product created: {}", createdProduct);
        categoryClient.addProduct(product.getCategoryId(),createdProduct);
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product>updateProduct(@PathVariable Long id,
                                                @RequestBody Product product){

        product = productService.updateProduct(id, product);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id){
        LOGGER.info("Product deleted ");
        productService.deleteProduct(id);
        return ResponseEntity.ok(" Product deleted successfully!.");
    }


}
