package org.library.exception.response;

import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorResponse {

  private List<ErrorModel> errorMessage;
}
