package org.library.exception;

import lombok.NonNull;

public class JwtVerificationException  extends AbstractFormattedException {

  public JwtVerificationException(@NonNull String message, Object... params) {
    super(message, params);
  }
}
