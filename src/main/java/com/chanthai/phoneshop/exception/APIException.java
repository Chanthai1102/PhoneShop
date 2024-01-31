package com.chanthai.phoneshop.exception;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
@Data
@RequiredArgsConstructor
public class APIException extends RuntimeException {
    private final HttpStatus httpStatus;
    private final String message;
}
