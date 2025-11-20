package com.github.name23523535.smartstorage.dto.qrCodeDto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class QrCodeResponseDto {
    @NotBlank
    private String type;

    @NotBlank
    private String link;
}
