package org.library.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.library.enums.ContentStatus;
import org.library.exception.EntityNotFoundException;
import org.library.model.ContentTag;
import org.library.model.UserContent;
import org.library.repository.UserContentRepository;
import org.springframework.stereotype.Service;

/**
 * The service which contains CRUD operations related to the Content.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ContentService {
    @NonNull
    private final UserContentRepository userContentRepository;
    @NonNull
    private final ContentTagService tagService;
    @NonNull
    private final UserService userService;

    /**
     * Creates new Content.
     *
     * <p>save new Content with unique uuid, otherwise throw validation exception.
     *
     * @param userContent Model Transfer Object for Content.
     * @return model transfer object UserContent.
     */
    public UserContent createContent(UserContent userContent) {
        final String tag = userContent.getContentTag().getTag();
        final ContentTag tagFromDb = tagService.findByContentTag(tag);
        userContent.setContentTag(tagFromDb);
        userContent.setStatus(ContentStatus.NEW);
        userContent.setUser(userService.findUserById(1L));

        return userContentRepository.save(userContent);
    }

    /**
     * Gets Content details by ID.
     *
     * <p>get Content details as a factory transfer object, otherwise throw NotFound exception.
     *
     * @param id - Content ID.
     * @return model transfer object UserContent.
     */
    public UserContent readContent(long id) {
        return userContentRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Content with id %d not found.", id));
    }

    /**
     * Updates Content details by ID.
     *
     * <p>update Content details by ID, otherwise throw Validation exception.
     *
     * @param id          -  Content ID.
     * @param userContent - Content factory transfer object for update.
     * @return model transfer object UserContent.
     */
    public UserContent updateContent(long id, UserContent userContent) {
        final UserContent contentFromDb = userContentRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Content with id %d not found.", id));
        contentFromDb.setLink(userContent.getLink());
        contentFromDb.setName(userContent.getName());
        contentFromDb.setDescription(userContent.getDescription());
        contentFromDb.setPriority(userContent.getPriority());
        contentFromDb.setStatus(userContent.getStatus());
        contentFromDb.setRating(userContent.getRating());
        final String tag = userContent.getContentTag().getTag();
        contentFromDb.setContentTag(tagService.findByContentTag(tag));
        return userContentRepository.save(contentFromDb);
    }

    /**
     * Delete content by it's uuid.
     *
     * @param id content id.
     */
    public void deleteContent(long id) {
        final UserContent content = userContentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Content with id %d not found.", id));

        userContentRepository.delete(content);
    }
}
