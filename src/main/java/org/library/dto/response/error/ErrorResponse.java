package org.library.dto.response.error;

import java.util.List;
import lombok.Builder;
import lombok.Data;

/**
 * Model that represents Dto error response.
 */
@Data
@Builder
public class ErrorResponse {

  private int errorCode;
  private List<ErrorDto> errorMessage;
}
