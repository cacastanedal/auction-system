package com.exmaple.auctionsystem.auctionsystem.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CommonErrors {

  @ExceptionHandler(EntityNotFoundException.class)
  public ResponseEntity<String> handleException(Exception e){
    return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
  }
}
