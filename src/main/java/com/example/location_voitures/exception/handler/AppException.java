package com.example.location_voitures.exception.handler;

import com.example.location_voitures.exception.EntityAlreadyExists;
import com.example.location_voitures.exception.EntityNotFoundException;
import com.example.location_voitures.shared.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.management.ObjectName;
import java.util.Date;

@RestControllerAdvice
public class AppException {

    @ExceptionHandler(value = {EntityNotFoundException.class})
    ResponseEntity<Object> entityNotFoundException(EntityNotFoundException ex){
        ErrorMessage errorMessage = ErrorMessage.builder()
                .message(ex.getMessage())
                .timestamp(new Date())
                .code(404)
                .build();
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(value = {EntityAlreadyExists.class})
    ResponseEntity<Object> entityAlreadyExists(EntityAlreadyExists ex){
        ErrorMessage errorMessage = ErrorMessage.builder()
                .message(ex.getMessage())
                .timestamp(new Date())
                .code(409)
                .build();
        return new ResponseEntity<>(errorMessage, HttpStatus.CONFLICT);

    }

}
