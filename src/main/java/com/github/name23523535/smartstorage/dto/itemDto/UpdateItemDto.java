package com.github.name23523535.smartstorage.dto.itemDto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateItemDto {
    @NotBlank
    private String name;

    private String description;

    @NotNull
    private Long categoryId;

    @NotNull
    @Min(0)
    private Integer count = 0;
}
