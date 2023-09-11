package org.library.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Component
@ConfigurationProperties(prefix = "elastic")
@Data
public class ElasticSearchProperties {

    @NotBlank
    private String host;
    @Positive
    private int port;
}
