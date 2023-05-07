package org.library.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class SearchContentDto {

    private int total;
    private List<ContentDto> contents;
}
