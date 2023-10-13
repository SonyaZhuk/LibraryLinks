package org.library.dto.request;

import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateContentTagDto {

    @NotBlank(message = "tag is required")
    private String tag;
}
