package org.library.dto.response;

import lombok.Builder;
import lombok.Data;
import org.library.enums.ContentStatus;
import org.library.enums.ContentType;
import org.library.enums.Priority;

import java.time.Instant;

@Data
@Builder
public class ContentDto {

    private String link;
    private ContentType type;
    private String tag;
    private String name;
    private String description;
    private Priority priority;
    private ContentStatus status;
    private Integer rating;
    private Instant createdDate;
    private Instant updatedDate;
}
