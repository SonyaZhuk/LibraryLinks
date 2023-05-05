package org.library.controller;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.library.controller.api.endpoints.ContentEndpoints;
import org.library.dto.request.CreateContentDto;
import org.library.dto.request.UpdateContentDto;
import org.library.dto.response.ContentDto;
import org.library.mapper.ContentMapper;
import org.library.model.UserContent;
import org.library.service.UserContentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * Controller implements endpoints for the CRUD
 * operation Content document.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(ContentEndpoints.CONTENT)
public class UserContentController {
    @NonNull
    private final ContentMapper mapper;
    @NonNull
    private final UserContentService userContentService;

    /**
     * Returns created Content.
     *
     * @param dto - Content factory transfer object.
     * @return created ContentDto object.
     */
    @ResponseStatus(HttpStatus.OK)
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public String create(@RequestBody final CreateContentDto dto) {
        final UserContent content = mapper.toModel(dto);
        return userContentService.createContent(content);
    }

    /**
     * Returns Content details.
     *
     * @param id - Content ID.
     * @return ContentDto object.
     */
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(ContentEndpoints.ID_RELATIVE_PATH)
    public ContentDto read(@PathVariable final String id) {
        return mapper.toDto(userContentService.readContent(id));
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
    public String update(@PathVariable final String id,
                             final @RequestBody UpdateContentDto dto) {
        final UserContent content = mapper.toModel(dto);
        return userContentService.updateContent(id, content);
    }

    /**
     * Deletes Content.
     *
     * @param id - Content ID.
     * @return successful status.
     */
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(ContentEndpoints.ID_RELATIVE_PATH)
    public void delete(@PathVariable final String id) {
        userContentService.deleteContent(id);
    }
}
