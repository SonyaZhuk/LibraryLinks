package org.library.exception;

import lombok.NonNull;

public class DuplicateLinkException extends AbstractFormattedException {

  public DuplicateLinkException(@NonNull String message, Object... params) {
    super(message, params);
  }
}
