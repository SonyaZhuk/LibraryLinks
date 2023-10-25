package org.library.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.library.common.AbstractTest;
import org.library.common.GenericTestData;
import org.library.exception.EntityNotFoundException;
import org.library.model.ContentTag;
import org.library.model.UserContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

@Slf4j
@DisplayName("user content service test suite")
@Sql(scripts = "classpath:__files/drop_all_h2.sql",
    executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class ContentServiceTest extends AbstractTest {
  @Autowired
  private ContentTagService contentTagService;
  @Autowired
  private ContentService contentService;

  @BeforeEach
  public void init() {
    final ContentTag tag = GenericTestData.generateContentTag();
    contentTagService.createContentTag(tag.getTag());
  }

  @Test
  @DisplayName("should save content")
  public void shouldSaveContent() {
    //arrange
    final UserContent userContent = GenericTestData.generateUserContent();

    //act
    final UserContent savedUserContent = contentService.createContent(userContent);

    //assert
    assertThat(savedUserContent.getId()).isNotNull();
  }

  @Test
  @DisplayName("should read content")
  public void shouldReadContent() {
    //arrange
    final UserContent userContent = GenericTestData.generateUserContent();
    contentService.createContent(userContent);

    //act
    final UserContent savedUserContent = contentService.readContent(1L);

    //assert
    assertThat(savedUserContent.getId()).isNotNull();
  }

  @Test
  @DisplayName("should update content")
  public void shouldUpdateContent() {
    //arrange
    final UserContent userContent = GenericTestData.generateUserContent();
    final UserContent savedUserContent = contentService.createContent(userContent);
    userContent.setName("new content name");

    //act
    final UserContent updatedUserContent =
        contentService.updateContent(savedUserContent.getId(), userContent);

    //assert
    assertThat(updatedUserContent.getId()).isNotNull();
    assertThat(updatedUserContent.getName()).isEqualTo("new content name");
  }

  @Test
  @DisplayName("should delete content")
  public void shouldDeleteContent() {
    //arrange
    final UserContent userContent = GenericTestData.generateUserContent();
    final UserContent savedUserContent = contentService.createContent(userContent);

    //act
    contentService.deleteContent(savedUserContent.getId());

    //assert
    assertThatThrownBy(() -> contentService.readContent(savedUserContent.getId()))
        .isInstanceOf(EntityNotFoundException.class)
        .hasMessage("Content with id " + savedUserContent.getId() + " not found.");
  }
}
