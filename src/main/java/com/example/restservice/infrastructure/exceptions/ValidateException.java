package com.example.restservice.infrastructure.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ValidateException extends RuntimeException {
	
	public ValidateException(String message) {
        super(message);
    }
	
    public ValidateException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
