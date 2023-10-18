package org.library.dto.response.error;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 * Model that represents service error response.
 */
@Data
@Builder
public class ServiceErrorResponse {

  private int status;
  private HttpStatus responseStatus;
  private String errorMessage;
}
