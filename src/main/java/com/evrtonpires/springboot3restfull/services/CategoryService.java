package com.evrtonpires.springboot3restfull.services;

import com.evrtonpires.springboot3restfull.domain.Category;
import com.evrtonpires.springboot3restfull.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    public Category getById(UUID id) {
        Optional<Category> categoryO = categoryRepository.findById(id);
        return categoryO.orElse(null);
    }

    public Category save(Category category) {

        return categoryRepository.save(category);
    }


    public Object update(UUID id, Category category) {
        Optional<Category> categoryO = categoryRepository.findById(id);
        if (categoryO.isEmpty()) {
            return "Product not found.";
        }


        return categoryRepository.save(category);
    }


    public void delete(UUID id) {
        Optional<Category> categoryO = categoryRepository.findById(id);
        categoryO.<Object>map(
                category -> {
                    categoryRepository.delete(categoryO.get());
                    return "Product deleted successfully.";
                }).orElseGet(() -> "Product not found.");
    }

}
