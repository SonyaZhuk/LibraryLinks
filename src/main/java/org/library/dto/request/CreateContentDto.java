package org.library.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import org.library.enums.ContentStatus;
import org.library.enums.ContentType;
import org.library.enums.Priority;

@Data
@Builder
public class CreateContentDto {

    @NotBlank
    private String link;
    @NotNull(message = "type is required")
    private ContentType type;
    @NotBlank
    private String tag;
    @NotBlank
    private String name;
    @NotBlank
    private String description;
    @NotNull(message = "priority is required")
    private Priority priority;
    @NotNull(message = "status is required")
    private ContentStatus status;
    @NotNull
    private Integer rating;
}
