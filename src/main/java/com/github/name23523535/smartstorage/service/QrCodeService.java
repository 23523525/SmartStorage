package com.github.name23523535.smartstorage.service;

import com.github.name23523535.smartstorage.dto.qrCodeDto.QrCodeResponseDto;
import com.github.name23523535.smartstorage.exception.InvalidQrException;
import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeWriter;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;

@Service
public class QrCodeService {
    public String generateQrCode(String payload) {
        try {

            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix bitMatrix = qrCodeWriter.encode(payload, BarcodeFormat.QR_CODE, 300, 300);

            ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
            MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream);
            byte[] pngData = pngOutputStream.toByteArray();

            return Base64.getEncoder().encodeToString(pngData);

        } catch (WriterException | IOException e) {
            throw new RuntimeException(e);}
    }

    public String decodeQrCode(String base64Qr) {
        try {
            // Декодируем Base64 в массив байт
            byte[] imageBytes = Base64.getDecoder().decode(base64Qr);

            // Преобразуем в BufferedImage
            BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(imageBytes));

            // Создаем BinaryBitmap для ZXing
            LuminanceSource source = new BufferedImageLuminanceSource(bufferedImage);
            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

            // Декодируем QR-код
            Result result = new MultiFormatReader().decode(bitmap);

            return result.getText();

        } catch (Exception e) {
            throw new RuntimeException("Failed to decode QR code", e);
        }
    }

    public QrCodeResponseDto handlePayload(String payload) {
        QrCodeResponseDto dto = new QrCodeResponseDto();
        String payloadString = decodeQrCode(payload);

        String[] parts = payloadString.split(":");
        String type = parts[0].toLowerCase();

        if (type.equals("item")) {
            if (parts.length != 2) {
                throw new InvalidQrException("Invalid QR code");
            }
            long id;
            try {
                id = Long.parseLong(parts[1]);
            } catch (NumberFormatException e) {
                throw new InvalidQrException("Invalid ID in QR code");
            }
            dto.setType(type);
            dto.setLink("/api/items/" + id);
            return dto;
        }
        throw new InvalidQrException("Invalid type in QR code");
    }
}
