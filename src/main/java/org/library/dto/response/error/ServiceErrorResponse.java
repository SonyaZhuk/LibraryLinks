package org.library.dto.response.error;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ServiceErrorResponse {

  private String errorMessage;
}
