package com.depromeet.tifyapi.spring;

import com.depromeet.tifyapi.Exception.ApiFailedException;
import com.depromeet.tifyapi.Exception.BadRequestException;
import com.depromeet.tifyapi.Exception.NoContentException;
import org.springframework.dao.DataAccessException;
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

    @ExceptionHandler(value = DataAccessException.class)
    public ResponseEntity<String> handleDataAccessException(DataAccessException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = BadRequestException.class)
    public ResponseEntity<String> handleBadRequestException(BadRequestException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = ApiFailedException.class)
    public ResponseEntity<String> handleApiFailedException(ApiFailedException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
