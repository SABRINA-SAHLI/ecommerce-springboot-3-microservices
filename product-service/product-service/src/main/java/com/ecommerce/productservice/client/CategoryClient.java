package com.ecommerce.productservice.client;


import com.ecommerce.productservice.model.Product;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PutExchange;

@HttpExchange
public interface CategoryClient {

    @PutExchange("/api/v1/categories/add-product/{id}")
    public void addProduct(@PathVariable Long id, @RequestBody Product product);


}
