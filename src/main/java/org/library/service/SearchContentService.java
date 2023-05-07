package org.library.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.library.model.ContentTag;
import org.library.model.UserContent;
import org.library.repository.UserContentRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class SearchContentService {
    @NonNull
    private final UserContentRepository userContentRepository;
    @NonNull
    private final ContentTagService contentTagService;

    public List<UserContent> findContentsByTagWithPaging(String tag, int page, int size) {
        final ContentTag contentTag = contentTagService.findByTag(tag);
        final Pageable pageable = PageRequest.of(page, size, Sort.by("createdDate").descending());
        return userContentRepository.findByContentTag(contentTag, pageable).getContent();
    }
}
