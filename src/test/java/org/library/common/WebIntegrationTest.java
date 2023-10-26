package org.library.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.extension.ExtendWith;
import org.library.controller.ContentController;
import org.library.controller.ContentTagController;
import org.library.controller.SearchContentController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@Slf4j
@SuppressWarnings("LineLength")
@ActiveProfiles(value = "it")
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = {ContentController.class, ContentTagController.class, SearchContentController.class})
public abstract class WebIntegrationTest {
  @Autowired
  protected ObjectMapper objectMapper;
  @Autowired
  protected MockMvc mockMvc;
}
