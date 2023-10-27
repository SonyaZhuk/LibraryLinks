package org.library.controller;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.library.common.GenericTestData;
import org.library.common.WebIntegrationTest;
import org.library.controller.api.endpoints.ContentEndpoints;
import org.library.dto.request.CreateContentDto;
import org.library.dto.request.UpdateContentDto;
import org.library.dto.response.ContentDto;
import org.library.mapper.UserContentMapper;
import org.library.model.UserContent;
import org.library.service.ContentService;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(ContentController.class)
@DisplayName("content controller test suite")
public class ContentControllerTest extends WebIntegrationTest {
  @MockBean
  private UserContentMapper mapper;
  @MockBean
  private ContentService contentService;
  @Test
  public void shouldCreateContent() throws Exception {
    //arrange
    final CreateContentDto dto = GenericTestData.generateCreateContentDto();
    final UserContent userContent = GenericTestData.generateUserContent();
    final ContentDto response = GenericTestData.generateContentDto();

    when(mapper.toModel(dto)).thenReturn(userContent);
    when(contentService.createContent(userContent)).thenReturn(userContent);
    when(mapper.toDto(userContent)).thenReturn(response);

    //act-assert
    mockMvc.perform(MockMvcRequestBuilders.post(ContentEndpoints.CONTENT_PATH)
             .content(objectMapper.writeValueAsString(dto))
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().string(objectMapper.writeValueAsString(response)));
  }
  @Test
  public void shouldGetContent() throws Exception {
    //arrange
    final UserContent userContent = GenericTestData.generateUserContent();
    final ContentDto response = GenericTestData.generateContentDto();

    when(contentService.readContent(anyLong())).thenReturn(userContent);
    when(mapper.toDto(userContent)).thenReturn(response);

    //act-assert
    mockMvc.perform(MockMvcRequestBuilders
            .get(ContentEndpoints.CONTENT_PATH +
                ContentEndpoints.ID_RELATIVE_PATH, 1l)
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().string(objectMapper.writeValueAsString(response)));
  }
  @Test
  public void shouldUpdateContent() throws Exception {
    //arrange
    final UpdateContentDto updateContentDto = GenericTestData.generateUpdateContentDto();
    final UserContent userContent = GenericTestData.generateUserContent();
    final ContentDto response = GenericTestData.generateContentDto();

    when(mapper.toModel(updateContentDto)).thenReturn(userContent);
    when(contentService.updateContent(1l, userContent)).thenReturn(userContent);
    when(mapper.toDto(userContent)).thenReturn(response);

    //act-assert
    mockMvc.perform(MockMvcRequestBuilders
        .put(ContentEndpoints.CONTENT_PATH +
                ContentEndpoints.ID_RELATIVE_PATH, 1l)
            .content(objectMapper.writeValueAsString(updateContentDto))
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().string(objectMapper.writeValueAsString(response)));
  }
  @Test
  public void shouldDeleteContent() throws Exception {
    //arrange-act-assert
    mockMvc.perform(MockMvcRequestBuilders
            .delete(ContentEndpoints.CONTENT_PATH +
                ContentEndpoints.ID_RELATIVE_PATH, 1l)
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
  }
}
