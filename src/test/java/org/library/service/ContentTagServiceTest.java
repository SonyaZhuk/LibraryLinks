package org.library.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Instant;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.library.common.AbstractTest;
import org.library.model.ContentTag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

@Slf4j
@DisplayName("content tag service test suite")
@Sql(scripts = "classpath:__files/drop_all_h2.sql",
    executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class ContentTagServiceTest extends AbstractTest {

  private static final String TAG_VALUE = "TEST";
  @Autowired
  private ContentTagService contentTagService;

  @BeforeEach
  public void init() {
    contentTagService.createContentTag(TAG_VALUE);
  }

  @Test
  @DisplayName("should save content tag")
  public void shouldSaveTag() {
    //arrange
    final String tag = TAG_VALUE + "1";

    //act
    final ContentTag savedContentTag = contentTagService.createContentTag(tag);

    //assert
    assertThat(savedContentTag.getId()).isNotNull();
    assertThat(savedContentTag.getTag()).isEqualTo(tag.toUpperCase());
    assertThat(savedContentTag.getCreatedDate()).isBeforeOrEqualTo(Instant.now());
    assertThat(savedContentTag.getUpdatedDate()).isBeforeOrEqualTo(Instant.now());
  }

  @Test
  @DisplayName("should find by content tag")
  public void shouldFindByTag() {
    //arrange-act
    final ContentTag savedContentTag = contentTagService.findByContentTag(TAG_VALUE);

    //assert
    assertThat(savedContentTag.getId()).isNotNull();
    assertThat(savedContentTag.getTag()).isEqualTo(TAG_VALUE);
    assertThat(savedContentTag.getCreatedDate()).isBeforeOrEqualTo(Instant.now());
    assertThat(savedContentTag.getUpdatedDate()).isBeforeOrEqualTo(Instant.now());
  }

  @Test
  @DisplayName("should find all content tags")
  public void shouldFindAllTags() {
    //arrange-act
    final List<String> tags = contentTagService.findAllContentTags();

    //assert
    assertThat(tags.size()).isEqualTo(1);
  }
}
