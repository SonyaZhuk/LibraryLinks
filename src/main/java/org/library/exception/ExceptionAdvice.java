package org.library.exception;

import java.util.List;
import java.util.stream.Collectors;
import org.library.dto.response.error.ErrorDto;
import org.library.dto.response.error.ErrorResponse;
import org.library.dto.response.error.ServiceErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Controller Advice that handles service and dto errors response.
 */
@ControllerAdvice
@ResponseBody
public class ExceptionAdvice {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorResponse handleException(MethodArgumentNotValidException exception) {

    final List<ErrorDto> errorMessages = exception.getBindingResult().getFieldErrors().stream()
        .map(err -> new ErrorDto(err.getField(), err.getRejectedValue(), err.getDefaultMessage()))
        .distinct()
        .collect(Collectors.toList());
    return ErrorResponse.builder().status(HttpStatus.BAD_REQUEST.value())
        .responseStatus(HttpStatus.BAD_REQUEST)
        .errorMessage(errorMessages).build();
  }

  @ExceptionHandler(EntityNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ServiceErrorResponse handleException(EntityNotFoundException exception) {
    return ServiceErrorResponse.builder().status(HttpStatus.NOT_FOUND.value())
        .responseStatus(HttpStatus.NOT_FOUND)
        .errorMessage(exception.getMessage()).build();
  }

  @ExceptionHandler(DuplicateLinkException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ServiceErrorResponse handleException(DuplicateLinkException exception) {
    return ServiceErrorResponse.builder().status(HttpStatus.BAD_REQUEST.value())
        .responseStatus(HttpStatus.BAD_REQUEST)
        .errorMessage(exception.getMessage()).build();
  }
}
