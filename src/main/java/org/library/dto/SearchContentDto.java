package org.library.dto;

import lombok.Builder;
import lombok.Data;
import org.library.enums.ContentStatus;
import org.library.enums.ContentType;
import org.library.enums.Priority;

@Data
@Builder
public class SearchContentDto {

    private String tag; //Java, Python...
    private ContentType type; // ARTICLE, BOOK, VIDEO, COURSE...
    private Priority priority; //(HIGH, LOW, MEDIA)
    private ContentStatus status; //NEW, IN_PROGRESS...
}
