package org.library.controller;

import java.util.List;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.library.controller.api.endpoints.ContentEndpoints;
import org.library.dto.response.ContentDto;
import org.library.dto.response.SearchContentDto;
import org.library.mapper.UserContentMapper;
import org.library.service.SearchContentService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

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
                                        @RequestParam(required = false, defaultValue = "0")
                                        int page,
                                        @RequestParam(required = false, defaultValue = "0")
                                        int size) {
    final List<ContentDto> contents = mapper.toDtos(
        searchContentService.findContentsByTagWithPaging(tag, page, size));
    return SearchContentDto.builder().total(contents.size()).contents(contents).build();
  }
}
