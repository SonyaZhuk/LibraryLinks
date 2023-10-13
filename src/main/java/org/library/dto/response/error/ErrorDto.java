package org.library.dto.response.error;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorDto {

  private String fieldName;
  private Object rejectedValue;
  private String messageError;
}
