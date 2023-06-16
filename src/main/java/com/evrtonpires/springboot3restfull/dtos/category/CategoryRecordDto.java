package com.evrtonpires.springboot3restfull.dtos.category;


import jakarta.validation.constraints.NotBlank;

public record CategoryRecordDto(@NotBlank String name) {
}