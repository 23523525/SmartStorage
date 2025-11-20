package com.github.name23523535.smartstorage.controller;

import com.github.name23523535.smartstorage.dto.qrCodeDto.QrCodeRequestDto;
import com.github.name23523535.smartstorage.dto.qrCodeDto.QrCodeResponseDto;
import com.github.name23523535.smartstorage.service.QrCodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/qr")
@RequiredArgsConstructor
public class QrCodeController {

    private final QrCodeService qrCodeService;

    @PostMapping
    public QrCodeResponseDto handleQr(@RequestBody QrCodeRequestDto dto) {
        return qrCodeService.handlePayload(dto.getQrCode());
    }
}
