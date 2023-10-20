package org.library.config;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.library.controller.api.endpoints.ContentEndpoints;
import org.library.security.CustomJwtDecoder;
import org.library.security.CustomJwtGrantedAuthoritiesConverter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;

@Slf4j
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf().disable()
        .sessionManagement(
            sessionManagementCustomizer -> sessionManagementCustomizer.sessionCreationPolicy(
                SessionCreationPolicy.NEVER))
        .authorizeRequests(authorizeRequests ->
            authorizeRequests
                .antMatchers(GET, "/actuator/**")
                .permitAll()
                .antMatchers(GET, ContentEndpoints.TAG_PATH)
                .hasAnyAuthority("ADMIN", "USER")
                .antMatchers(POST, ContentEndpoints.TAG_PATH)
                .hasAnyAuthority("ADMIN")
                .antMatchers(ContentEndpoints.CONTENT_PATH)
                .permitAll()
                .anyRequest().authenticated()
        )
        .oauth2ResourceServer(oauth2ResourceServer ->
            oauth2ResourceServer
                .jwt(jwt -> {
                      jwt.decoder(jwtDecoder())
                          .jwtAuthenticationConverter(customJwtAuthenticationConverter());
                    }
                )
        );
  }

  private JwtDecoder jwtDecoder() {
    return new CustomJwtDecoder(jwtPublicKeys());
  }

  @Bean
  @ConfigurationProperties(prefix = "jwt.public.keys")
  public Map<String, String> jwtPublicKeys() {
    return new HashMap<>();
  }

  private Converter<Jwt, ? extends AbstractAuthenticationToken> customJwtAuthenticationConverter() {
    final JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
    if (customJwtConverter() != null) {
      jwtAuthenticationConverter
          .setJwtGrantedAuthoritiesConverter(customJwtConverter());
    }
    return jwtAuthenticationConverter;
  }

  @Bean
  public Converter<Jwt, Collection<GrantedAuthority>> customJwtConverter() {
    return new CustomJwtGrantedAuthoritiesConverter();
  }
}
