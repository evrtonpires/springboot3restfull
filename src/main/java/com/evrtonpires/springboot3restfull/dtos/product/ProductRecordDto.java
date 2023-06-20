package com.evrtonpires.springboot3restfull.dtos.product;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProductRecordDto(@NotBlank(message = "o campo name não pode ser vazio") String name,
                               @NotNull(message = "o campo preco não pode ser nulo") double preco) {
}