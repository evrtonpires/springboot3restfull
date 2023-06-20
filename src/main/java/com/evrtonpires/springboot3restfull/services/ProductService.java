package com.evrtonpires.springboot3restfull.services;

import com.evrtonpires.springboot3restfull.domain.Product;
import com.evrtonpires.springboot3restfull.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public List<Product> getAll() {
        return productRepository.findAll();
    }

    public Product getById(UUID id) {
        Optional<Product> categoryO = productRepository.findById(id);
        return categoryO.orElse(null);
    }

    public Product save(Product category) {

        return productRepository.save(category);
    }

    public List<Product> saveAll(List<Product> categories) {

        return productRepository.saveAll(categories);
    }


    public Object update(UUID id, Product category) {
        Optional<Product> categoryO = productRepository.findById(id);
        if (categoryO.isEmpty()) {
            return "Product not found.";
        }


        return productRepository.save(category);
    }


    public void delete(UUID id) {
        Optional<Product> categoryO = productRepository.findById(id);
        categoryO.<Object>map(
                category -> {
                    productRepository.delete(categoryO.get());
                    return "Product deleted successfully.";
                }).orElseGet(() -> "Product not found.");
    }

}
