package org.library.controller;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.library.controller.api.endpoints.ContentEndpoints;
import org.library.dto.request.CreateContentDto;
import org.library.dto.request.UpdateContentDto;
import org.library.dto.response.ContentDto;
import org.library.mapper.UserContentMapper;
import org.library.model.UserContent;
import org.library.service.ContentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(ContentEndpoints.CONTENT)
public class ContentController {

    @NonNull
    private final UserContentMapper mapper;
    @NonNull
    private final ContentService contentService;

    /**
     * Returns created Content.
     *
     * @param dto - Content factory transfer object.
     * @return created ContentDto object.
     */
    @ResponseStatus(HttpStatus.OK)
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ContentDto create(@RequestBody final CreateContentDto dto) {
        UserContent userContent = mapper.toModel(dto);
        return mapper.toDto(contentService.createContent(userContent));
    }

    /**
     * Returns Content details.
     *
     * @param id - Content ID.
     * @return ContentDto object.
     */
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(ContentEndpoints.ID_RELATIVE_PATH)
    public ContentDto read(@PathVariable final long id) {
        return mapper.toDto(contentService.readContent(id));
    }

    /**
     * Returns updated Content details.
     *
     * @param id - Content ID.
     * @param dto - Content factory for update.
     * @return updated ContentDto object.
     */
    @ResponseStatus(HttpStatus.OK)
    @PutMapping(ContentEndpoints.ID_RELATIVE_PATH)
    public ContentDto update(@PathVariable final long id,
                         final @RequestBody UpdateContentDto dto) {
        final UserContent content = mapper.toModel(dto);
        return mapper.toDto(contentService.updateContent(id, content));
    }

    /**
     * Deletes Content.
     *
     * @param id - Content ID.
     * @return successful status.
     */
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(ContentEndpoints.ID_RELATIVE_PATH)
    public void delete(@PathVariable final long id) {
        contentService.deleteContent(id);
    }
}
