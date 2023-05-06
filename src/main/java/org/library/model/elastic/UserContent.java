package org.library.model.elastic;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.library.enums.ContentStatus;
import org.library.enums.ContentType;
import org.library.enums.Priority;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserContent {

    private String link;
    @JsonProperty("content_type")
    private ContentType type;
    private String tag;
    private String name;
    private String description;
    private Priority priority;
    private ContentStatus status;
    private Integer rating;
    @JsonProperty("created_date")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ")
    private Instant createdDate;
    @JsonProperty("updated_date")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ")
    private Instant updatedDate;
}
