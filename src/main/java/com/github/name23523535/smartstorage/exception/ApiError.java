package com.github.name23523535.smartstorage.exception;

import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
public class ApiError {
    private OffsetDateTime timestamp = OffsetDateTime.now();
    private int status;
    private String error;
    private String message;


    public ApiError(int status, String error, String message) {
        this.status = status;
        this.error = error;
        this.message = message;
    }
}
