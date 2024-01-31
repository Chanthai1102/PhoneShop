package com.chanthai.phoneshop.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class ErrorReponse {
    private HttpStatus status;
    private String message;
}
