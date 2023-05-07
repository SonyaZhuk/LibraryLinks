package org.library.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.library.model.ContentTag;
import org.library.repository.ContentTagRepository;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ContentTagService {

    @NonNull
    private final ContentTagRepository repository;

    public ContentTag createContentTag(ContentTag tag) {
        ContentTag newTag = new ContentTag();
        newTag.setTag(tag.getTag().toUpperCase());
        return repository.save(newTag);
    }

    public ContentTag findByTag(String tag) {
        return repository.findByTag(tag).orElseThrow();
    }
}
