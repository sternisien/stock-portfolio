package org.example.infrastructure.controller_advice;

import org.example.infrastructure.controller_advice.common.ApiError;
import org.example.infrastructure.exception.BadRequestException;
import org.example.infrastructure.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {

  @ExceptionHandler({ResourceNotFoundException.class})
  public ApiError resourceNotFoundException(ResourceNotFoundException ex) {

    return new ApiError(
        ex.getMessage(),
        HttpStatus.NOT_FOUND.value(),
        HttpStatus.NOT_FOUND.name(),
        ex.getResourceType(),
        ex.getLocalDateTime());
  }

  @ExceptionHandler({BadRequestException.class})
  public ApiError badRequestException(BadRequestException ex) {

    return new ApiError(
        ex.getMessage(),
        HttpStatus.BAD_REQUEST.value(),
        HttpStatus.BAD_REQUEST.name(),
        ex.getResourceType(),
        ex.getLocalDateTime());
  }
}
