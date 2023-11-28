package com.ecommerce.categoryservice.controller;


import com.ecommerce.categoryservice.Service.CategoryService;
import com.ecommerce.categoryservice.model.Category;
import com.ecommerce.categoryservice.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

    private final Logger LOGGER = LoggerFactory.getLogger(CategoryController.class);
    @Autowired
    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }



    @GetMapping
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }


    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long id){
        LOGGER.info("Find category: id = {} ", id);
        Optional<Category> category = categoryService.getCategoryById(id);
        return category.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody Category category){

        Category createdCategory = categoryService.addCategory(category);
        return new ResponseEntity<>(createdCategory,HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable Long id, @RequestBody Category updatedCategory) {
        Category category = categoryService.updateCategory(id, updatedCategory);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id){
        LOGGER.info("Category deleted ");
        categoryService.deleteCategory(id);
        return ResponseEntity.ok(" Category deleted successfully!.");
    }



    @PutMapping("/add-product/{id}")
    public ResponseEntity<Category>addProduct(@PathVariable Long id, @RequestBody Product product) {
        LOGGER.info("Add Product to a category  ");
        Category category = categoryService.addProduct(id, product);
        return new ResponseEntity<>(category, HttpStatus.OK);

    }


}
