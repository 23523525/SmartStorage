package com.github.name23523535.smartstorage.dto.categoryDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class CategoryResponseDto {
    @NotNull
    @Positive
    private Long id;

    @NotBlank
    private String name;

    private String description;
}
