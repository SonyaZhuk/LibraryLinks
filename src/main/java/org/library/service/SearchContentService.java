package org.library.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.library.model.ContentTag;
import org.library.model.UserContent;
import org.library.repository.UserContentRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The service which contains Search operations related to the Content.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class SearchContentService {
  @NonNull
  private final UserContentRepository userContentRepository;
  @NonNull
  private final ContentTagService contentTagService;

  /**
   * Searches contents by its tag.
   *
   * @param tag Content Tag.
   * @return relevant list UserContent.
   */
  public List<UserContent> findContentsByTagWithPaging(String tag, int page, int size) {
    final ContentTag contentTag = contentTagService.findByContentTag(tag);

    final Pageable pageable = (page >= 0 && size > 0) ? PageRequest.of(page, size) : null;

    log.info("search by content tag: tag={}", tag);
    return userContentRepository.findByContentTag(contentTag, pageable).getContent();
  }
}
