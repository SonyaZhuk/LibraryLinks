package org.library.dto;

import lombok.Builder;
import lombok.Data;
import org.library.enums.ContentType;
import org.library.enums.Priority;

@Data
@Builder
public class CreateContentDto {

    private String name;
    private String description;
    private String tag;
    private String link;
    private ContentType type;
    private Priority priority;
    private Integer rating;
}
