package org.library.controller;

import javax.validation.Valid;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.library.controller.api.endpoints.ContentEndpoints;
import org.library.dto.response.ContentDto;
import org.library.dto.response.SearchContentDto;
import org.library.mapper.UserContentMapper;
import org.library.service.SearchContentService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(ContentEndpoints.CONTENT_PATH)
public class SearchContentController {
  @NonNull
  private final SearchContentService searchContentService;
  @NonNull
  private final UserContentMapper mapper;

  @ResponseStatus(HttpStatus.OK)
  @GetMapping
  public SearchContentDto searchContent(@RequestParam String tag,
                                        @Valid @PositiveOrZero(message = "page should be positive number")
                                        @RequestParam(required = false, defaultValue = "0")
                                        int page,
                                        @Valid @Positive(message = "size should be positive number")
                                        @RequestParam(required = false, defaultValue = "0")
                                        int size) {
    List<ContentDto> contents = mapper.toDtos(
        searchContentService.findContentsByTagWithPaging(tag, page, size));
    return SearchContentDto.builder().total(contents.size()).contents(contents).build();
  }
}
