package com.project.todolist.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ExceptionsHandler {
    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<ConflictExceptionDetails> handleConflictException(ConflictException conflict){
        return new ResponseEntity<>(
                ConflictExceptionDetails.builder()
                        .title("Conflict error")
                        .status(HttpStatus.CONFLICT.value())
                        .details(conflict.getMessage())
                        .developerMessage(conflict.getClass().getName())
                        .timestamp(LocalDateTime.now())
                        .build(), HttpStatus.CONFLICT);
    }
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<NotFoundExceptionDetails> handleConflictException(NotFoundException notFound){
        return new ResponseEntity<>(
                NotFoundExceptionDetails.builder()
                        .title("Not Found")
                        .status(HttpStatus.CONFLICT.value())
                        .details(notFound.getMessage())
                        .developerMessage(notFound.getClass().getName())
                        .timestamp(LocalDateTime.now())
                        .build(), HttpStatus.CONFLICT);
    }
}
