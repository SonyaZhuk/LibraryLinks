package org.library.dto.response.error;

import java.util.List;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 * Model that represents Dto error response.
 */
@Data
@Builder
public class ErrorResponse {

  private int status;
  private HttpStatus responseStatus;
  private List<ErrorDto> errorMessage;
}
