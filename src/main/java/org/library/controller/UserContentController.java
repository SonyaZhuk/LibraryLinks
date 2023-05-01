package org.library.controller;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.library.dto.CreateContentDto;
import org.library.mapper.ContentToContentDtoMapper;
import org.library.service.UserContentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/contents")
public class UserContentController {
    @NonNull
    private final ContentToContentDtoMapper mapper;
    @NonNull
    private final UserContentService userContentService;

    //TODO: move to
//    @ResponseStatus(HttpStatus.OK)
//    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
//    public List<SearchUserContentDto> receiveContents(@RequestParam(value = "storeCode") String storeCode) {
//        return mapper.toDtos(userContentService.searchContent());
//    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public CreateContentDto saveContent(CreateContentDto userContent) {
        return mapper.toCreateDto(userContentService.saveContent(mapper.toModel(userContent)));
    }
}
