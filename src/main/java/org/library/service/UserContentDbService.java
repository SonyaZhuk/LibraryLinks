package org.library.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.library.model.ContentTag;
import org.library.model.UserContent;
import org.library.repository.UserContentRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;

/**
 * The service which contains CRUD operations related to the Content.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UserContentDbService {

    @NonNull
    private final UserContentRepository userContentRepository;
    @NonNull
    private final TagService tagService;
    @NonNull
    private final UserService userService;

    /**
     * Creates new Content.
     *
     * <p>save new Content with unique uuid, otherwise throw validation exception.
     *
     * @param userContent Data Transfer Object for Content.
     * @return model transfer object UserContent.
     */
    public UserContent createContent(UserContent userContent) {
        final ContentTag tagFromDb = tagService.findByTag(userContent.getContentTag().getTag());
        userContent.setContentTag(tagFromDb);
        userContent.setUser(userService.findUserById(1L));

        return userContentRepository.saveAndFlush(userContent);
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
        return userContentRepository.findById(id).orElseThrow();
    }

    /**
     * Updates Content details by ID.
     *
     * <p>update Content details by ID, otherwise throw Validation exception.
     *
     * @param id -  Content ID.
     * @param userContent - Content factory transfer object for update.
     * @return model transfer object UserContent.
     */
    public UserContent updateContent(long id, UserContent userContent) {
        UserContent contentFromDb = userContentRepository.findById(id).orElseThrow();
        contentFromDb.setLink(userContent.getLink());
        contentFromDb.setName(userContent.getName());
        contentFromDb.setDescription(userContent.getDescription());
        contentFromDb.setPriority(userContent.getPriority());
        contentFromDb.setStatus(userContent.getStatus());
        contentFromDb.setRating(userContent.getRating());
        contentFromDb.setContentTag(tagService.findByTag(userContent.getContentTag().getTag()));
        contentFromDb.setUpdatedDate(Instant.now());
        return userContentRepository.saveAndFlush(contentFromDb);
    }

    /**
     * Delete content by it's uuid
     *
     * @param id content id
     */
    public void deleteContent(long id) {
        final UserContent content = userContentRepository.findById(id).orElseThrow();

        userContentRepository.delete(content);
    }
}
