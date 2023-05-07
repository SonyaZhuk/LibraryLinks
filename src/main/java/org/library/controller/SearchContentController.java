package org.library.controller;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.library.controller.api.endpoints.ContentEndpoints;
import org.library.dto.response.ContentDto;
import org.library.dto.response.SearchContentDto;
import org.library.model.UserContent;
import org.library.mapper.UserContentMapper;
import org.library.service.SearchContentService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(ContentEndpoints.CONTENT)
public class SearchContentController {

    @NonNull
    private final SearchContentService searchContentService;
    @NonNull
    private final UserContentMapper mapper;


    @ResponseStatus(HttpStatus.OK)
    @GetMapping(ContentEndpoints.TAG_RELATIVE_PATH)
    public SearchContentDto findContents(@PathVariable String tag,
                                                @RequestParam int page, @RequestParam int size) {
        List<UserContent> content = searchContentService.findAllContentByTagWithPaging(tag, page, size);
        List<ContentDto> contents = mapper.toDtos(content);
        return SearchContentDto.builder().total(contents.size()).contents(contents).build();
    }
}
