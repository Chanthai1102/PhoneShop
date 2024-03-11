package com.chanthai.phoneshop.exception;

import org.springframework.http.HttpStatus;

public class ResourceNotFoundException extends APIException{
    public ResourceNotFoundException(String resourceName,Long id) {
        super(HttpStatus.NOT_FOUND,String.format("%s With id = %d Not Found",resourceName,id));;
    }
}
