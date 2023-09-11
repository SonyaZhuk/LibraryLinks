package org.library.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class ContentTagDto {

    private Long id;
    private String tag;
    private Instant createdDate;
    private Instant updatedDate;
}
