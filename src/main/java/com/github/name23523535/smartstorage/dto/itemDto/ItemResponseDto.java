package com.github.name23523535.smartstorage.dto.itemDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public class ItemResponseDto {
    @NotNull
    @Positive
    private Long id;

    @NotBlank
    private String name;

    private String description;

    @NotNull
    @PositiveOrZero
    private Integer count;

    @NotNull
    @Positive
    private Long categoryId;

    @NotBlank
    private String categoryName;

    @NotBlank
    private String qrCode;
}
