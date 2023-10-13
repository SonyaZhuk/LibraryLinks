package org.library.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Data;
import org.library.enums.ContentStatus;
import org.library.enums.ContentType;
import org.library.enums.Priority;

@Data
public class CreateContentDto {

    @NotBlank
    private String link;
    @NotNull(message = "type cannot be empty")
    private ContentType type;
    @NotBlank
    private String tag;
    @NotBlank
    private String name;
    @NotBlank
    private String description;
    @NotNull(message = "priority cannot be empty")
    private Priority priority;
    @NotNull(message = "status cannot be empty")
    private ContentStatus status;
    @NotNull
    private Integer rating;
}
