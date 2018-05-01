package com.depromeet.tifyapi.spring;

import com.depromeet.tifyapi.Exception.NoContentException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {
    @ExceptionHandler(value = NoContentException.class)
    public ResponseEntity<String> handleNoContentException() {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
