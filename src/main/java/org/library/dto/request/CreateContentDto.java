package org.library.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.library.enums.ContentStatus;
import org.library.enums.ContentType;
import org.library.enums.Priority;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateContentDto {

    @NotBlank
    private String link;
    @NotBlank
    private ContentType type;
    @NotBlank
    private String tag;
    @NotBlank
    private String name;
    @NotBlank
    private String description;
    @NotBlank
    private Priority priority;
    @NotBlank
    private ContentStatus status;
    @NotBlank
    private Integer rating;
}
