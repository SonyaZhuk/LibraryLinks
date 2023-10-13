package org.library.dto.response.error;

import java.util.List;
import lombok.Builder;
import lombok.Data;
import org.library.dto.response.error.ErrorDto;

@Data
@Builder
public class ErrorResponse {

  private List<ErrorDto> errorMessage;
}
