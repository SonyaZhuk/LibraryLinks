package org.library.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.library.common.AbstractTest;
import org.library.common.GenericTestData;
import org.library.model.UserContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

@Slf4j
@DisplayName("search content tag service test suite")
@Sql(scripts = "classpath:__files/drop_all_h2.sql",
    executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class SearchContentServiceTest extends AbstractTest {

  @Autowired
  private ContentTagService contentTagService;
  @Autowired
  private ContentService contentService;
  @Autowired
  private SearchContentService searchContentService;

  @Test
  @DisplayName("should search content")
  public void shouldSearchContent() {
    //arrange
    final String tag = "TEST";
    contentTagService.createContentTag(tag);
    final UserContent userContent = GenericTestData.generateUserContent();

    contentService.createContent(userContent);

    //act
    final List<UserContent> contents =
        searchContentService.findContentsByTagWithPaging(tag, 0, 0);

    //assert
    assertThat(contents.size()).isEqualTo(1);
  }
}
