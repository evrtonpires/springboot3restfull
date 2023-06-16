package com.evrtonpires.springboot3restfull.resources;

import com.evrtonpires.springboot3restfull.domain.Category;
import com.evrtonpires.springboot3restfull.dtos.category.CategoryRecordDto;
import com.evrtonpires.springboot3restfull.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class CategoryResource {


    @Autowired
    CategoryService categoryService;

    @GetMapping("/categories")
    public ResponseEntity<List<Category>> getAll() {
        List<Category> productsList = categoryService.getAll();
        if (!productsList.isEmpty()) {
            for (Category category : productsList) {
                UUID id = category.getId();
                category.add(linkTo(methodOn(CategoryResource.class).getById(id)).withSelfRel());
            }
        }

        return ResponseEntity.status(HttpStatus.OK).body(productsList);
    }

    @GetMapping("/categories/{id}")
    public ResponseEntity<Object> getById(@PathVariable(value = "id") UUID id) {
        Category category = categoryService.getById(id);
        if (category == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).
                    body("Product not found.");
        }
        category.add(linkTo(methodOn(CategoryResource.class).getAll()).withSelfRel());
        return ResponseEntity.status(HttpStatus.OK).
                body(category);

    }

//    @PostMapping("/categories")
//    public ResponseEntity<Category> save(@RequestBody @Valid CategoryRecordDto categoryRecordDto) {
//        var category = new Category();
//        BeanUtils.copyProperties(categoryRecordDto, category);
//
//        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.save(category));
//    }

    @PostMapping("/categories")
    public ResponseEntity<List<Category>> saveAll(@RequestBody @Valid List<CategoryRecordDto> categoryRecordDtos) {
        List<Category> categories = new ArrayList<>();
        for (CategoryRecordDto categoryR : categoryRecordDtos) {
            var category = new Category();
            BeanUtils.copyProperties(categoryR, category);
            categories.add(category);
        }


        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.saveAll(categories));
    }


    @PutMapping("/categories/{id}")
    public ResponseEntity<Object> updateProduct(@PathVariable(value = "id") UUID id, @RequestBody @Valid CategoryRecordDto categoryRecordDto) {
        Category category = categoryService.getById(id);
        if (category == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");
        }
        BeanUtils.copyProperties(categoryRecordDto, category);
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.update(id, category));
    }

    @DeleteMapping("/categories/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable(value = "id") UUID id) {
        Category category = categoryService.getById(id);

        if (category == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");
        }
        categoryService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("Product deleted successfully.");
    }

}
