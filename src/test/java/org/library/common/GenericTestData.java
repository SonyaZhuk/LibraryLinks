package org.library.common;

import java.time.Instant;
import lombok.experimental.UtilityClass;
import org.library.dto.request.CreateContentDto;
import org.library.dto.request.UpdateContentDto;
import org.library.dto.response.ContentDto;
import org.library.enums.ContentStatus;
import org.library.enums.ContentType;
import org.library.enums.Priority;
import org.library.model.ContentTag;
import org.library.model.UserContent;

@UtilityClass
public class GenericTestData {
  public UserContent generateUserContent() {
    final UserContent userContent = new UserContent();
    userContent.setStatus(ContentStatus.NEW);
    userContent.setRating(5);
    userContent.setContentTag(generateContentTag());
    userContent.setPriority(Priority.MEDIUM);
    userContent.setType(ContentType.ARTICLE);
    userContent.setName("test content");
    userContent.setDescription("test description");
    userContent.setLink("http://website.com");
    userContent.setCreatedDate(Instant.now());
    userContent.setUpdatedDate(Instant.now());
    return userContent;
  }
  public ContentTag generateContentTag() {
    final ContentTag contentTag = new ContentTag();
    contentTag.setTag("TEST");
    return contentTag;
  }
  public CreateContentDto generateCreateContentDto() {
    return CreateContentDto.builder()
        .link("http://")
        .type(ContentType.ARTICLE)
        .tag("TAG")
        .name("name")
        .description("description")
        .priority(Priority.MEDIUM)
        .status(ContentStatus.NEW)
        .rating(5)
        .build();
  }
  public UpdateContentDto generateUpdateContentDto() {
    return UpdateContentDto.builder()
        .link("http//")
        .tag("TAG")
        .type(ContentType.ARTICLE)
        .priority(Priority.MEDIUM)
        .name("name")
        .description("description")
        .status(ContentStatus.NEW)
        .rating(5)
        .build();
  }
  public ContentDto generateContentDto() {
    return ContentDto.builder()
        .link("http//")
        .tag("TAG")
        .type(ContentType.ARTICLE)
        .priority(Priority.MEDIUM)
        .name("name")
        .description("description")
        .status(ContentStatus.NEW)
        .rating(5)
        .build();
  }
}
