package com.github.name23523535.smartstorage.dto.movementDto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class RemoveStockDto {
    @NotNull
    @Positive
    private int amount;

    private String comment;
}
