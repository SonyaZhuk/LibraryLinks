package org.library.dto.response.error;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Model that represents Dto errors.
 */
@Data
@AllArgsConstructor
public class ErrorDto {

  private String fieldName;
  private Object rejectedValue;
  private String messageError;
}
