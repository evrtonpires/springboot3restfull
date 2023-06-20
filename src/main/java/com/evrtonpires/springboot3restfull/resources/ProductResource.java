package com.evrtonpires.springboot3restfull.resources;

import com.evrtonpires.springboot3restfull.domain.Product;
import com.evrtonpires.springboot3restfull.dtos.product.ProductRecordDto;
import com.evrtonpires.springboot3restfull.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Validated
@RestController
public class ProductResource {


    @Autowired
    ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAll() {
        List<Product> productsList = productService.getAll();
        if (!productsList.isEmpty()) {
            for (Product product : productsList) {
                UUID id = product.getId();
                product.add(linkTo(methodOn(ProductResource.class).getById(id)).withSelfRel());
            }
        }

        return ResponseEntity.status(HttpStatus.OK).body(productsList);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Object> getById(@PathVariable(value = "id") UUID id) {
        Product product = productService.getById(id);
        if (product == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).
                    body("Product not found.");
        }
        product.add(linkTo(methodOn(ProductResource.class).getAll()).withSelfRel());
        return ResponseEntity.status(HttpStatus.OK).
                body(product);

    }

    @PostMapping("/products")
    public ResponseEntity<Product> save(@RequestBody @Valid ProductRecordDto productRecordDto) {
        var product = new Product();
        BeanUtils.copyProperties(productRecordDto, product);


        return ResponseEntity.status(HttpStatus.CREATED).body(productService.save(product));
    }

//    @PostMapping("/products")
//    public ResponseEntity<List<Product>> saveAll(@RequestBody List<@Valid ProductRecordDto> productRecordDtos) {
//        List<Product> products = new ArrayList<>();
//        for (ProductRecordDto productR : productRecordDtos) {
//            var product = new Product();
//            BeanUtils.copyProperties(productR, product);
//            products.add(product);
//        }
//
//
//        return ResponseEntity.status(HttpStatus.CREATED).body(productService.saveAll(products));
//    }


    @PutMapping("/products/{id}")
    public ResponseEntity<Object> updateProduct(@PathVariable(value = "id") UUID id, @Valid @RequestBody ProductRecordDto productRecordDto) {
        Product product = productService.getById(id);
        if (product == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");
        }
        BeanUtils.copyProperties(productRecordDto, product);
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.update(id, product));
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable(value = "id") UUID id) {
        Product product = productService.getById(id);

        if (product == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");
        }
        productService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("Product deleted successfully.");
    }

}
