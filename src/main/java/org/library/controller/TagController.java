package org.library.controller;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.library.controller.api.endpoints.ContentEndpoints;
import org.library.dto.request.CreateContentTagDto;
import org.library.dto.response.ContentTagDto;
import org.library.mapper.ContentTagMapper;
import org.library.service.ContentTagService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(ContentEndpoints.TAG)
public class TagController {

    @NonNull
    private final ContentTagMapper mapper;
    @NonNull
    private final ContentTagService service;

    /**
     * Returns created Tag.
     *
     * @param dto - Tag transfer object.
     * @return created TagDto object.
     */
    @ResponseStatus(HttpStatus.OK)
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ContentTagDto create(@RequestBody final CreateContentTagDto dto) {
        return mapper.toDto(service.createContentTag(mapper.toModel(dto)));
    }
}
