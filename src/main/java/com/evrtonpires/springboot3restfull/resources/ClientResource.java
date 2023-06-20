package com.evrtonpires.springboot3restfull.resources;

import com.evrtonpires.springboot3restfull.domain.Client;
import com.evrtonpires.springboot3restfull.dtos.client.ClientRecordDto;
import com.evrtonpires.springboot3restfull.services.ClientService;
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
public class ClientResource {


    @Autowired
    ClientService clientService;

    @GetMapping("/clients")
    public ResponseEntity<List<Client>> getAll() {
        List<Client> productsList = clientService.getAll();
        if (!productsList.isEmpty()) {
            for (Client category : productsList) {
                UUID id = category.getId();
                category.add(linkTo(methodOn(ClientResource.class).getById(id)).withSelfRel());
            }
        }

        return ResponseEntity.status(HttpStatus.OK).body(productsList);
    }

    @GetMapping("/clients/{id}")
    public ResponseEntity<Object> getById(@PathVariable(value = "id") UUID id) {
        Client category = clientService.getById(id);
        category.add(linkTo(methodOn(ClientResource.class).getAll()).withSelfRel());
        return ResponseEntity.status(HttpStatus.OK).
                body(category);

    }

    @PostMapping("/clients")
    public ResponseEntity<Client> save(@RequestBody @Valid ClientRecordDto categoryRecordDto) {
        var category = new Client();
        BeanUtils.copyProperties(categoryRecordDto, category);

        return ResponseEntity.status(HttpStatus.CREATED).body(clientService.save(category));
    }

//    @PostMapping("/clients")
//    public ResponseEntity<List<Client>> saveAll(@RequestBody List<@Valid ClientRecordDto> categoryRecordDtos) {
//        List<Client> categories = new ArrayList<>();
//        for (ClientRecordDto categoryR : categoryRecordDtos) {
//            var category = new Client();
//            BeanUtils.copyProperties(categoryR, category);
//            categories.add(category);
//        }
//
//
//        return ResponseEntity.status(HttpStatus.CREATED).body(clientService.saveAll(categories));
//    }


    @PutMapping("/clients/{id}")
    public ResponseEntity<Object> updateProduct(@PathVariable(value = "id") UUID id, @Valid @RequestBody ClientRecordDto categoryRecordDto) {
        Client category = clientService.getById(id);
        if (category == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");
        }
        BeanUtils.copyProperties(categoryRecordDto, category);
        return ResponseEntity.status(HttpStatus.CREATED).body(clientService.update(id, category));
    }

    @DeleteMapping("/clients/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable(value = "id") UUID id) {
        Client category = clientService.getById(id);

        if (category == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");
        }
        clientService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("Product deleted successfully.");
    }

}
