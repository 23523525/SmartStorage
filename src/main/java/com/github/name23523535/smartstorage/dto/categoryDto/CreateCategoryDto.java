package com.github.name23523535.smartstorage.dto.categoryDto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateCategoryDto {
    @NotBlank
    private String name;

    private String description;
}
