package com.github.name23523535.smartstorage.dto.movementDto;

import jakarta.validation.constraints.Min;

import java.time.OffsetDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class MovementRequestDto {
    @NotNull
    @Positive
    private Long id;

    @NotNull
    @Positive
    private Long itemId;

    @NotBlank
    private String type; // IN or OUT

    @NotNull
    @Min(1)
    private Integer amount;

    private String comment;

    @NotNull
    private OffsetDateTime createdAt;
}
