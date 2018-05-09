package com.depromeet.tifyapi.Exception;

public class ApiFailedException extends RuntimeException{
    public ApiFailedException(String message) {
        super(message);
    }
}
