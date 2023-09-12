package org.library.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.library.enums.ContentStatus;
import org.library.enums.ContentType;
import org.library.enums.Priority;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateContentDto {

    private String link;
    private ContentType type;
    private String tag;
    private String name;
    private String description;
    private Priority priority;
    private ContentStatus status;
    private Integer rating;
}
