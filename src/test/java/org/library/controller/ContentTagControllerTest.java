package org.library.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.Instant;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.library.common.WebIntegrationTest;
import org.library.controller.api.endpoints.ContentEndpoints;
import org.library.dto.request.CreateContentTagDto;
import org.library.dto.response.ContentTagDto;
import org.library.mapper.ContentTagMapper;
import org.library.model.ContentTag;
import org.library.service.ContentTagService;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(ContentTagController.class)
@DisplayName("content tag controller test suite")
public class ContentTagControllerTest extends WebIntegrationTest {
  @MockBean
  private ContentTagMapper mapper;
  @MockBean
  private  ContentTagService service;

  @Test
  public void shouldCreateTag() throws Exception {
    //arrange
    final CreateContentTagDto dto = new CreateContentTagDto();
    dto.setTag("TAG");

    final ContentTagDto responseDto = ContentTagDto.builder().id(1L).tag("TAG")
        .createdDate(Instant.now())
        .updatedDate(Instant.now())
        .build();

    when(service.createContentTag(dto.getTag())).thenReturn(new ContentTag());
    when(mapper.toDto(any(ContentTag.class))).thenReturn(responseDto);

    //act-assert
    mockMvc.perform(MockMvcRequestBuilders.post(ContentEndpoints.TAG_PATH)
            .content(objectMapper.writeValueAsString(dto))
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().string(objectMapper.writeValueAsString(responseDto)));
  }
  @Test
  public void shouldGetAllTags() throws Exception {
    //arrange
    final List<String> response = List.of("TAG1", "TAG2");

    when(service.findAllContentTags()).thenReturn(response);

    //act-assert
    mockMvc.perform(MockMvcRequestBuilders.get(ContentEndpoints.TAG_PATH)
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().string(objectMapper.writeValueAsString(response)));
  }
}
