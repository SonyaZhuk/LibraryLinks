package org.library.exception.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorModel {

  private String fieldName;
  private Object rejectedValue;
  private String messageError;
}
