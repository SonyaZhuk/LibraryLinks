package org.library.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.library.controller.api.endpoints.ContentEndpoints;
import org.library.dto.response.ContentDto;
import org.library.dto.response.SearchContentDto;
import org.library.mapper.UserContentMapper;
import org.library.model.UserContent;
import org.library.service.SearchContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@Slf4j
@SuppressWarnings("LineLength")
@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(SearchContentController.class)
@DisplayName("search content controller test suite")
public class SearchContentControllerTest {
  @Autowired
  protected ObjectMapper objectMapper;
  @Autowired
  protected MockMvc mockMvc;
  @MockBean
  private SearchContentService searchContentService;
  @MockBean
  private UserContentMapper mapper;

  @Test
  public void shouldSearchContents() throws Exception {
    //arrange
    final String testTag = "TAG";
    final ContentDto dto = ContentDto.builder()
        .tag(testTag)
        .build();

    final SearchContentDto response = SearchContentDto.builder()
        .contents(List.of(dto))
        .total(1)
        .build();

    final UserContent content = new UserContent();

    when(searchContentService.findContentsByTagWithPaging(testTag, 0, 0))
        .thenReturn(List.of(content));
    when(mapper.toDtos(List.of(content)))
        .thenReturn(List.of(dto));

    //act-assert
    mockMvc.perform(MockMvcRequestBuilders.get(ContentEndpoints.CONTENT_PATH)
            .queryParam("tag", testTag)
            .content(objectMapper.writeValueAsString(dto))
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().string(objectMapper.writeValueAsString(response)));
  }
}
