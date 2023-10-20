package org.library.controller;

import java.util.List;
import javax.validation.Valid;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.library.controller.api.endpoints.ContentEndpoints;
import org.library.dto.request.CreateContentTagDto;
import org.library.dto.response.ContentTagDto;
import org.library.mapper.ContentTagMapper;
import org.library.service.ContentTagService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(ContentEndpoints.TAG_PATH)
public class ContentTagController {
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
    public ContentTagDto create(@Valid @RequestBody final CreateContentTagDto dto) {
        return mapper.toDto(service.createContentTag(dto.getTag()));
    }

    /**
     * Returns all created Tags.
     *
     * @return all created Tags from database.
     */
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<String> readAll() {
        return service.findAllContentTags();
    }
}
