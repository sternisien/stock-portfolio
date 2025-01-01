package org.example.infrastructure.controller_advice;

import org.example.infrastructure.controller_advice.common.ApiError;
import org.example.infrastructure.exception.ApiException;
import org.example.infrastructure.exception.DataValidationException;
import org.example.infrastructure.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {

  @ExceptionHandler({ResourceNotFoundException.class})
  public ResponseEntity<ApiError> resourceNotFoundException(ApiException ex) {
    HttpStatus status = HttpStatus.NOT_FOUND;

    return new ResponseEntity<>(
        new ApiError(
            ex.getMessage(),
            status.value(),
            status.name(),
            ex.getResourceType(),
            ex.getLocalDateTime()),
        status);
  }

  @ExceptionHandler({DataValidationException.class})
  public ResponseEntity<ApiError> badRequestException(ApiException ex) {
    HttpStatus status = HttpStatus.BAD_REQUEST;
    return new ResponseEntity<>(
        new ApiError(
            ex.getMessage(),
            status.value(),
            status.name(),
            ex.getResourceType(),
            ex.getLocalDateTime()),
        status);
  }
}
