package org.library.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.library.exception.EntityNotFoundException;
import org.library.model.ContentTag;
import org.library.repository.ContentTagRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ContentTagService {
    @NonNull
    private final ContentTagRepository repository;

    /**
     * Creates new Tag.
     *
     * <p>save new Tag with unique id, otherwise throw validation exception.
     *
     * @param tag - the tag title.
     * @return model transfer object for a tag.
     */
    public ContentTag createContentTag(String tag) {
        final ContentTag newTag = new ContentTag();
        newTag.setTag(tag.toUpperCase());
        ContentTag savedContentTag = repository.save(newTag);
        log.info("content tag is saved: id={}", savedContentTag.getId());
        return savedContentTag;
    }

    /**
     * Gets Tag details by Tag title.
     *
     * <p>get Tag details as a factory transfer object, otherwise throw NotFound exception.
     *
     * @param tag - the tag title.
     * @return model transfer object for a tag.
     */
    public ContentTag findByContentTag(String tag) {
        return repository.findByTag(tag.toUpperCase()).orElseThrow(() ->
                new EntityNotFoundException("Tag with title %s not found.", tag));
    }

    /**
     * Gets the list of Tags.
     *
     * <p>get existing Tag's list from database.
     *
     * @return tags list.
     */
    public List<String> findAllContentTags() {
        return repository.findAll().stream()
                .map(ContentTag::getTag)
                .collect(Collectors.toList());
    }
}
