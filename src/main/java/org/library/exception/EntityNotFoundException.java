package org.library.exception;

import lombok.NonNull;

public class EntityNotFoundException extends AbstractFormattedException {

    public EntityNotFoundException(@NonNull String message, Object... params) {
        super(message, params);
    }
}
