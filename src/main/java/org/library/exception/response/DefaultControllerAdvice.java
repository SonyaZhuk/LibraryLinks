package org.library.exception.response;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@ResponseBody
public class DefaultControllerAdvice {

  @ExceptionHandler(value = MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorResponse handleException(MethodArgumentNotValidException exception) {

    final List<ErrorModel> errorMessages = exception.getBindingResult().getFieldErrors().stream()
        .map(err -> new ErrorModel(err.getField(), err.getRejectedValue(), err.getDefaultMessage()))
        .distinct()
        .collect(Collectors.toList());
    return ErrorResponse.builder().errorMessage(errorMessages).build();
  }
}
