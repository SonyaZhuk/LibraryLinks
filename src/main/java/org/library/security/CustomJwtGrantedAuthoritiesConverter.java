package org.library.security;

import java.util.Collection;
import java.util.List;
import javax.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CustomJwtGrantedAuthoritiesConverter implements
    Converter<Jwt, Collection<GrantedAuthority>> {

  /**
   * Extract {@link GrantedAuthority}s from the given {@link Jwt}.
   *
   * @param jwt The {@link Jwt} token
   * @return The {@link GrantedAuthority authorities} read from the token scopes
   */
  @Override
  public Collection<GrantedAuthority> convert(@NotNull Jwt jwt) {
    SimpleGrantedAuthority s = new SimpleGrantedAuthority(jwt.getClaim("groups"));
    return List.of(new SimpleGrantedAuthority(jwt.getClaim("groups")));
  }
}
