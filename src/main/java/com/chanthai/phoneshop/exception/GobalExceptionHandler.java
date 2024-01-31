package com.chanthai.phoneshop.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GobalExceptionHandler {
    @ExceptionHandler(APIException.class)
    public ResponseEntity<?> handleApiException(APIException e){
        ErrorReponse errorReponse = new ErrorReponse(e.getHttpStatus(),e.getMessage());
        return ResponseEntity
                .status(e.getHttpStatus())
                .body(errorReponse);
    }
}
