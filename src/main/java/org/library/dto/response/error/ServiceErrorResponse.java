package org.library.dto.response.error;

import lombok.Builder;
import lombok.Data;

/**
 * Model that represents service error response.
 */
@Data
@Builder
public class ServiceErrorResponse {

  private int errorCode;
  private String errorMessage;
}
