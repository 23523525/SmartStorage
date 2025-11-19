package com.github.name23523535.smartstorage.dto.categoryDto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UpdateCategoryDto {
    @NotBlank
    private String name;

    private String description;
}
