package org.library.common;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import org.junit.jupiter.api.extension.ExtendWith;
import org.library.Application;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ActiveProfiles(profiles = "test")
@ContextConfiguration(classes = Application.class)
@SpringBootTest(
    properties = {
        "spring.cloud.discovery.enabled=false",
        "spring.h2.console.enabled=true"
    },
    webEnvironment = RANDOM_PORT)
@ExtendWith({SpringExtension.class})
public class AbstractTest {
}
