package com.evrtonpires.springboot3restfull.dtos.client;


import com.evrtonpires.springboot3restfull.domain.Address;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.Set;

public record ClientRecordDto(@NotBlank(message = "o campo 'name' não pode ser vazio") String name,
                              @NotBlank(message = "o campo 'email' não pode ser vazio") String email,
                              @NotBlank(message = "o campo 'cpfOurCnpj' não pode ser vazio") String cpfOurCnpj,
                              @NotNull(message = "o campo 'type' não pode ser nulo") Integer type,
                              @NotNull @NotEmpty(message = "o campo 'addresses' não pode ser vazio") List<Address> addresses,
                              @NotNull @NotEmpty(message = "o campo 'phones' não pode ser vazio") Set<String> phones) {
}