package com.ecommerce.categoryservice.Service;


import com.ecommerce.categoryservice.model.Category;
import com.ecommerce.categoryservice.model.Product;
import com.ecommerce.categoryservice.repository.CategoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private final Logger LOGGER = LoggerFactory.getLogger(Category.class);

    @Autowired
    private CategoryRepository categoryRepository;


    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Optional<Category> getCategoryById(Long id) {

       return categoryRepository.findById(id);
    }


    public List<Category> getAllCategories() {
        LOGGER.info("fetch all categories...");
        return categoryRepository.findAll();

    }

    public Category addCategory(Category category) {
        LOGGER.info("adding category...");
        return categoryRepository.save(category);
    }

    public Category updateCategory(Long id,Category category) {
        LOGGER.info("update category...");
        if (categoryRepository.existsById(id)) {
            category.setId(id);
            return categoryRepository.save(category);
        } else {
            throw new RuntimeException("Category not found");
        }
    }

    public void deleteCategory(Long id) {
        LOGGER.info("update category...");
        if (categoryRepository.existsById(id)) {
            categoryRepository.deleteById(id);
        } else {
            throw new RuntimeException("Category not found");
        }

    }

    public Category addProduct(Long id, Product product) {
        LOGGER.info("adding product to category..."+ product);
        Optional<Category>  category = categoryRepository.findById(id);

       if(category.isPresent()){
            LOGGER.info("Category found :"+ category.get().getId());
            Product productToAdd = new Product();
            productToAdd.setId(product.getId());
            productToAdd.setCategory(category.get());
            productToAdd.setTitle(product.getTitle());
            productToAdd.setSellingPrice(product.getSellingPrice());
            category.get().getProducts().add(productToAdd);
            Category categoryCreated =  categoryRepository.save(category.get());
         //  LOGGER.info("The products are : "+categoryCreated.getProducts().size());
         //  LOGGER.info("The products are : "+categoryCreated.getProducts().toString());
           return categoryCreated;
        } else {
            throw new RuntimeException("Category not found");
        }

    }
}
